package com.example.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = GuruAmber,
    onPrimary = GuruNavyDark,
    primaryContainer = GuruAmberDark,
    onPrimaryContainer = GuruAmberLight,
    secondary = GuruSky,
    onSecondary = GuruNavyDark,
    tertiary = GuruEmerald,
    onTertiary = GuruNavyDark,
    background = GuruNavyDark,
    onBackground = GuruTextPrimary,
    surface = GuruNavy,
    onSurface = GuruTextPrimary,
    surfaceVariant = GuruNavySurface,
    onSurfaceVariant = GuruTextSecondary,
    outline = GuruNavyBorder,
    error = GuruRose,
    onError = GuruTextPrimary
)

private val LightColorScheme = lightColorScheme(
    primary = GuruAmberDark,
    onPrimary = GuruSurfaceLight,
    primaryContainer = GuruAmberLight,
    onPrimaryContainer = GuruNavyDark,
    secondary = GuruSky,
    onSecondary = GuruSurfaceLight,
    tertiary = GuruEmerald,
    onTertiary = GuruSurfaceLight,
    background = GuruBackgroundLight,
    onBackground = GuruTextPrimaryLight,
    surface = GuruSurfaceLight,
    onSurface = GuruTextPrimaryLight,
    surfaceVariant = GuruCardLight,
    onSurfaceVariant = GuruTextSecondaryLight,
    outline = GuruNavyBorder,
    error = GuruRose,
    onError = GuruSurfaceLight
)

@Composable
fun HamroSapanaTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = GuruAmber.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun AmbitionGuruTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    HamroSapanaTheme(darkTheme = false, content = content)
}
