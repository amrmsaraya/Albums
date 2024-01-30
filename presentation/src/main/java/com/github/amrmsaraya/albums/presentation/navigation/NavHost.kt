package com.github.amrmsaraya.albums.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.github.amrmsaraya.albums.presentation.screen.profile.profileRoute
import com.github.amrmsaraya.albums.presentation.screen.profile.profileScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    onShowSnackbar: suspend (message: String, actionLabel: String?) -> Boolean,
    modifier: Modifier = Modifier,
    startDestination: String = profileRoute
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { slideInHorizontallyEnter() },
        popEnterTransition = { slideInHorizontallyPopEnter() },
        exitTransition = { slideOutHorizontallyExit() },
        popExitTransition = { slideOutHorizontallyPopExit() }
    ) {
        profileScreen(
            onShowSnackbar = onShowSnackbar,
            onNavigateToPhotos = {}
        )
    }
}