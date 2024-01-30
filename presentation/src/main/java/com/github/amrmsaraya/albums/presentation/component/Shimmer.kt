package com.github.amrmsaraya.albums.presentation.component

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import xyz.podio.stories.core.designsystem.component.PlaceholderHighlight
import xyz.podio.stories.core.designsystem.component.fade
import xyz.podio.stories.core.designsystem.component.placeholder

@Composable
fun Shimmer(modifier: Modifier = Modifier, cornerRadius: Dp = 0.dp) {
    Spacer(
        modifier = modifier
            .placeholder(
                visible = true,
                shape = RoundedCornerShape(cornerRadius),
                color = MaterialTheme.colorScheme.outline,
                highlight = PlaceholderHighlight.fade(
                    highlightColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.75f),
                    animationSpec = infiniteRepeatable(
                        animation = tween(delayMillis = 200, durationMillis = 700),
                        repeatMode = RepeatMode.Reverse,
                    )
                )
            )
    )
}