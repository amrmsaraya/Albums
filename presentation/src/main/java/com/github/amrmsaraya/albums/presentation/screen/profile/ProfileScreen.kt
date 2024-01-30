package com.github.amrmsaraya.albums.presentation.screen.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.amrmsaraya.albums.presentation.R
import com.github.amrmsaraya.albums.presentation.component.Shimmer

@Composable
fun ProfileScreen(
    state: ProfileState,
    onIntent: (ProfileIntent) -> Unit,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    onNavigateToPhotos: (albumId: Int, albumTitle: String) -> Unit
) {
    val address by remember(state.user) {
        mutableStateOf(
            listOf(
                state.user?.address?.street ?: "",
                state.user?.address?.suite ?: "",
                state.user?.address?.city ?: "",
                state.user?.address?.zipcode ?: "",
            )
                .filter { it.isNotBlank() }
                .joinToString(", ") { it }
        )
    }

    LaunchedEffect(state.error) {
        if (state.error != null) {
            onShowSnackbar(state.error, null)
            onIntent(ProfileIntent.ResetError)
        }
    }

    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.profile),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.size(24.dp))
        if (state.isUsersLoading) UserLoading()
        else if (state.user != null) {
            Text(
                text = state.user.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = address,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(R.string.my_albums),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            when {
                state.isAlbumsLoading -> items(4) { AlbumLoading() }
                else -> items(
                    items = state.albums,
                    key = { it.id }
                ) { album ->
                    Album(
                        album.title, showDivider = album.id != state.albums.lastOrNull()?.id,
                        onClick = { onNavigateToPhotos(album.id, album.title) }
                    )
                }
            }
        }
    }
}

@Composable
private fun UserLoading() {
    Column {
        Shimmer(
            Modifier
                .fillMaxWidth(0.4f)
                .height(18.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(12.dp))
        Shimmer(
            Modifier
                .fillMaxWidth()
                .height(14.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
private fun AlbumLoading() {
    Column {
        Spacer(modifier = Modifier.size(16.dp))
        Shimmer(
            Modifier
                .fillMaxWidth()
                .height(16.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.size(16.dp))
        HorizontalDivider()
    }
}

@Composable
private fun Album(
    title: String,
    showDivider: Boolean,
    onClick: () -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(vertical = 16.dp),
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
        if (showDivider) HorizontalDivider()
    }
}