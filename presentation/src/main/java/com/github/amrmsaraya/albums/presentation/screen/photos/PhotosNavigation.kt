package com.github.amrmsaraya.albums.presentation.screen.photos

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import java.net.URLDecoder
import java.net.URLEncoder

private const val ALBUM_ID = "albumId"
private const val ALBUM_TITLE = "albumTitle"
internal const val photosRoute = "photosRoute/{$ALBUM_ID}/{$ALBUM_TITLE}"

internal fun NavController.navigateToPhotos(
    albumId: Int,
    albumTitle: String,
    navOptions: NavOptions? = null
) {
    navigate(
        route = photosRoute
            .replace("{$ALBUM_ID}", albumId.toString())
            .replace("{$ALBUM_TITLE}", URLEncoder.encode(albumTitle)),
        navOptions = navOptions
    )
}

internal fun NavGraphBuilder.photosScreen(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    onBackClick: () -> Unit
) {
    composable(route = photosRoute,
        arguments = listOf(
            navArgument(ALBUM_ID) { type = NavType.IntType }
        )
    ) { entry ->
        val viewModel = hiltViewModel<PhotosViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        PhotosScreen(
            state = state,
            onIntent = viewModel::sendIntent,
            albumTitle = URLDecoder.decode(entry.arguments?.getString(ALBUM_TITLE) ?: ""),
            albumId = entry.arguments?.getInt(ALBUM_ID) ?: 0,
            onShowSnackbar = onShowSnackbar,
            onBackClick = onBackClick
        )
    }
}
