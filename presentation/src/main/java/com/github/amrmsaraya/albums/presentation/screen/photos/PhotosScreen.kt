package com.github.amrmsaraya.albums.presentation.screen.photos

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.amrmsaraya.albums.domain.entity.photo.Photo
import com.github.amrmsaraya.albums.presentation.R
import com.github.amrmsaraya.albums.presentation.component.AppImage
import com.github.amrmsaraya.albums.presentation.component.AppTopBar
import com.github.amrmsaraya.albums.presentation.component.Shimmer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotosScreen(
    state: PhotosState,
    onIntent: (PhotosIntent) -> Unit,
    albumTitle: String,
    albumId: Int,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    onBackClick: () -> Unit
) {
    var query by rememberSaveable { mutableStateOf("") }
    val filteredPhotos by remember(query, state.photos) {
        mutableStateOf(state.photos.filter { it.title.contains(query) })
    }
    var selectedPhoto by remember { mutableStateOf<Photo?>(null) }

    LaunchedEffect(albumId) {
        onIntent(PhotosIntent.GetPhotos(albumId))
    }

    LaunchedEffect(state.error) {
        if (state.error != null) {
            onShowSnackbar(state.error, null)
            onIntent(PhotosIntent.ResetError)
        }
    }

    selectedPhoto?.let { photo ->
        PhotoPreviewBottomSheet(
            photo,
            onDismissRequest = { selectedPhoto = null }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { AppTopBar(title = albumTitle, onBackClick = onBackClick) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = { query = it },
                singleLine = true,
                shape = CircleShape,
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                placeholder = {
                    Text(
                        text = "Search in images..",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                textStyle = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.size(16.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.spacedBy(1.dp),
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                if (state.isLoading) {
                    items(20) {
                        Shimmer(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)

                        )
                    }
                } else {
                    items(
                        items = filteredPhotos,
                        key = { it.id }
                    ) { photo ->
                        AppImage(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .clickable { selectedPhoto = photo },
                            url = photo.thumbnailUrl
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PhotoPreviewBottomSheet(
    selectedPhoto: Photo,
    onDismissRequest: () -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { it == SheetValue.Hidden }
    )

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        dragHandle = null,
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(12.dp))
                .clipToBounds(),
            propagateMinConstraints = true
        ) {
            var scale by remember { mutableFloatStateOf(1f) }
            var offset by remember { mutableStateOf(Offset(0f, 0f)) }
            val transformableState =
                rememberTransformableState { zoomChange, panChange, rotationChange ->
                    scale = (scale * zoomChange).coerceIn(1f, 4f)

                    val extraWidth = (scale - 1) * constraints.maxWidth
                    val extraHeight = (scale - 1) * constraints.maxHeight

                    val maxX = extraWidth / 2
                    val maxY = extraHeight / 2

                    offset = Offset(
                        x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                        y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY),
                    )
                }

            AppImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationX = offset.x
                        translationY = offset.y
                    }
                    .transformable(transformableState),
                url = selectedPhoto.url,
                contentScale = ContentScale.Fit
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            onClick = {
                scope.launch { sheetState.hide() }
                    .invokeOnCompletion { onDismissRequest() }

                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, selectedPhoto.url)
                    context.startActivity(Intent.createChooser(this, "Choose"))
                }
            }
        ) {
            Text(text = stringResource(R.string.share))
        }
    }
}