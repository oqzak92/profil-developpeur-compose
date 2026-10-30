package com.example.profildeveloppeur.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

// Thème clair aux couleurs de Qoul
private val LightColorScheme = lightColorScheme(
    primary = QoulVert,
    onPrimary = QoulBlanc,
    primaryContainer = QoulVertClair,
    onPrimaryContainer = QoulVertFonce,
    secondary = QoulVertFonce,
    secondaryContainer = QoulGrisFond,
    onSecondaryContainer = QoulNoir,
    errorContainer = QoulRougeClair,
    onErrorContainer = QoulRouge,
    background = QoulGrisFond,
    onBackground = QoulNoir,
    surface = QoulGrisFond,
    onSurface = QoulNoir,
    surfaceVariant = QoulBlanc,
    onSurfaceVariant = QoulNoir
)

// Thème sombre aux couleurs de Qoul
private val DarkColorScheme = darkColorScheme(
    primary = QoulVert,
    onPrimary = QoulBlanc,
    primaryContainer = QoulVertSombre,
    onPrimaryContainer = QoulVert,
    secondary = QoulVert,
    secondaryContainer = QoulNoirFond,
    onSecondaryContainer = QoulBlanc,
    background = QoulNoirFond,
    onBackground = QoulBlanc,
    surface = QoulNoirFond,
    onSurface = QoulBlanc,
    surfaceVariant = QoulGrisCarte,
    onSurfaceVariant = QoulBlanc
)

@Composable
fun ProfilDeveloppeurTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Pas de couleurs dynamiques (Android 12+) : on garde toujours les couleurs de Qoul
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
