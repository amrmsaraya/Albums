package com.github.amrmsaraya.albums.presentation.screen.profile

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

internal const val profileRoute = "ProfileRoute"

internal fun NavController.navigateToProfile(navOptions: NavOptions? = null) {
    navigate(
        route = profileRoute,
        navOptions = navOptions
    )
}

internal fun NavGraphBuilder.profileScreen(
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    onNavigateToPhotos: (albumId: Int) -> Unit
) {
    composable(route = profileRoute) {
        val viewModel = hiltViewModel<ProfileViewModel>()
        val state by viewModel.state.collectAsStateWithLifecycle()
        ProfileScreen(
            state = state,
            onIntent = viewModel::sendIntent,
            onShowSnackbar = onShowSnackbar,
            onNavigateToPhotos = onNavigateToPhotos
        )
    }
}
