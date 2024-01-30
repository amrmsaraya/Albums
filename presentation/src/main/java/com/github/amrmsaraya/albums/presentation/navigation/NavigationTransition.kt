package com.github.amrmsaraya.albums.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically

fun slideInHorizontallyEnter(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { 100 },
        animationSpec = tween(250)
    ) + fadeIn(animationSpec = tween(250))
}

fun slideOutHorizontallyExit(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { -100 },
        animationSpec = tween(250)
    ) + fadeOut(animationSpec = tween(250))
}

fun slideInHorizontallyPopEnter(): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { -100 },
        animationSpec = tween(250)
    ) + fadeIn(animationSpec = tween(250))
}

fun slideOutHorizontallyPopExit(): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { 100 },
        animationSpec = tween(250)
    ) + fadeOut(animationSpec = tween(250))
}

fun slideInVerticallyEnter(): EnterTransition {
    return slideInVertically(
        initialOffsetY = { 200 },
        animationSpec = tween(240)
    ) + fadeIn(animationSpec = tween(260))
}

fun slideOutVerticallyExit(): ExitTransition {
    return slideOutVertically(
        targetOffsetY = { 200 },
        animationSpec = tween(240)
    ) + fadeOut(animationSpec = tween(260))
}

fun slideInVerticallyPopEnter(): EnterTransition {
    return slideInVertically(
        initialOffsetY = { -200 },
        animationSpec = tween(240)
    ) + fadeIn(animationSpec = tween(260))
}

fun slideOutVerticallyPopExit(): ExitTransition {
    return slideOutVertically(
        targetOffsetY = { -200 },
        animationSpec = tween(240)
    ) + fadeOut(animationSpec = tween(260))
}