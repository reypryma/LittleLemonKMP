package org.re.kmplittlelemon.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- Brand palette (hex only, per your preference) ---
private val LL_Green        = Color(0xFF495E57)
private val LL_GreenDark    = Color(0xFF33443F)
private val LL_Yellow       = Color(0xFFF4CE14)
private val LL_Salmon       = Color(0xFFEE9972)
private val LL_Cloud        = Color(0xFFF7F7F7)
private val LL_TextDark     = Color(0xFF1B1B1B)
private val LL_TextOnDark   = Color(0xFFF7F7F7)
private val LL_Outline      = Color(0xFFCBD5E1)

private val LightColors = lightColorScheme(
    primary = LL_Green,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE9F0EE),
    onPrimaryContainer = LL_TextDark,

    secondary = LL_Yellow,
    onSecondary = Color(0xFF1B1B1B),
    secondaryContainer = Color(0xFFFFF1B8),
    onSecondaryContainer = Color(0xFF1B1B1B),

    tertiary = LL_Salmon,
    onTertiary = Color(0xFF1B1B1B),

    background = Color(0xFFFFFFFF),
    onBackground = LL_TextDark,

    surface = Color(0xFFFFFFFF),
    onSurface = LL_TextDark,
    surfaceVariant = LL_Cloud,
    onSurfaceVariant = Color(0xFF334155),

    outline = LL_Outline
)

private val DarkColors = darkColorScheme(
    primary = LL_Green,
    onPrimary = LL_TextOnDark,
    primaryContainer = LL_GreenDark,
    onPrimaryContainer = LL_TextOnDark,

    secondary = LL_Yellow,
    onSecondary = Color(0xFF1B1B1B),

    tertiary = LL_Salmon,
    onTertiary = Color(0xFF1B1B1B),

    background = Color(0xFF0F1513),
    onBackground = LL_TextOnDark,

    surface = Color(0xFF0F1513),
    onSurface = LL_TextOnDark,
    surfaceVariant = Color(0xFF18211E),
    onSurfaceVariant = Color(0xFFD8E3DF),

    outline = Color(0xFF334155)
)

// Typography tuned for “menu/restaurant” feel (clean + bold headings)
private val LL_Typography = Typography(
    headlineLarge = TextStyle(fontSize = 34.sp, fontWeight = FontWeight.Bold),
    headlineSmall = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.SemiBold),
    titleMedium   = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.SemiBold),
    bodyLarge     = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Normal),
    bodyMedium    = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Normal),
    labelLarge    = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
)

private val LL_Shapes = Shapes(
    extraSmall = androidx.compose.foundation.shape.RoundedCornerShape(10.dp),
    small      = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    medium     = androidx.compose.foundation.shape.RoundedCornerShape(16.dp),
    large      = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
    extraLarge = androidx.compose.foundation.shape.RoundedCornerShape(28.dp),
)

@Composable
fun LittleLemonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        typography = LL_Typography,
        shapes = LL_Shapes,
        content = content
    )
}
