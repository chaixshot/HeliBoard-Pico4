// SPDX-License-Identifier: GPL-3.0-only
package helium314.keyboard.latin.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import helium314.keyboard.latin.R

@Composable
fun Theme(dark: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val material3 = Typography()
    val colorScheme = if (dark) darkColorScheme(
        primary = colorResource(R.color.accent),
        onPrimary = colorResource(R.color.foreground),
        primaryContainer = colorResource(R.color.selected_accent),
        onPrimaryContainer = colorResource(R.color.foreground),
        background = colorResource(R.color.setup_background),
        onBackground = colorResource(R.color.foreground),
        surface = colorResource(R.color.setup_step_background),
        onSurface = colorResource(R.color.foreground),
        surfaceVariant = colorResource(R.color.action_bar_color),
        onSurfaceVariant = colorResource(R.color.foreground),
        outline = colorResource(R.color.foreground_weak)
    ) else lightColorScheme(
        primary = colorResource(R.color.accent),
        background = colorResource(R.color.setup_background),
        surface = colorResource(R.color.action_bar_color)
    )
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(
            titleLarge = material3.titleLarge.copy(fontWeight = FontWeight.Bold),
            titleMedium = material3.titleMedium.copy(fontWeight = FontWeight.Bold),
            titleSmall = material3.titleSmall.copy(fontWeight = FontWeight.Bold)
        ),
        //shapes = Shapes(),
        content = content
    )
}

const val previewDark = true
