package com.github.amrmsaraya.albums.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers

@Composable
fun AppImage(
    url: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    SubcomposeAsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .dispatcher(Dispatchers.IO)
            .data(url)
            .crossfade(500)
            .allowHardware(false)
            .build(),
        loading = { Shimmer(Modifier.matchParentSize()) },
        alignment = Alignment.Center,
        contentDescription = null,
        contentScale = contentScale,
    )
}