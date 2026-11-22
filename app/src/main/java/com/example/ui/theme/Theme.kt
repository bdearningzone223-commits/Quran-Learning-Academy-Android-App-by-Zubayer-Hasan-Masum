package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val AcademyLightColorScheme = lightColorScheme(
    primary = EmeraldPrimary,
    onPrimary = PureWhite,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = EmeraldDeep,
    secondary = GoldRich,
    onSecondary = PureWhite,
    secondaryContainer = GoldSoft,
    onSecondaryContainer = ColorGoldDark(),
    tertiary = EmeraldMid,
    onTertiary = PureWhite,
    tertiaryContainer = EmeraldSoft,
    onTertiaryContainer = EmeraldDeep,
    background = IvoryBackground,
    onBackground = InkDark,
    surface = PureWhite,
    onSurface = InkDark,
    surfaceVariant = SoftParchment,
    onSurfaceVariant = InkMuted,
    outline = OutlineLight,
    outlineVariant = GoldBorder
)

private val AcademyDarkColorScheme = darkColorScheme(
    primary = EmeraldLightDark,
    onPrimary = DarkBackground,
    primaryContainer = EmeraldDeep,
    onPrimaryContainer = EmeraldSoft,
    secondary = GoldLightDark,
    onSecondary = DarkBackground,
    secondaryContainer = DarkSurfaceVariant,
    onSecondaryContainer = GoldSoft,
    tertiary = GoldBright,
    onTertiary = DarkBackground,
    background = DarkBackground,
    onBackground = InkLight,
    surface = DarkSurface,
    onSurface = InkLight,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = InkMutedDark,
    outline = EmeraldMid,
    outlineVariant = GoldRich
)

private fun ColorGoldDark() = androidx.compose.ui.graphics.Color(0xFF6B4F0A)

val AcademyShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
    extraLarge = RoundedCornerShape(32.dp)
)

@Composable
fun QuranAcademyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) AcademyDarkColorScheme else AcademyLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AcademyTypography,
        shapes = AcademyShapes,
        content = content
    )
}
