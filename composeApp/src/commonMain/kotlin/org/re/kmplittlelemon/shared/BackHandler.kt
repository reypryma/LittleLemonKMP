package org.re.kmplittlelemon.shared

import androidx.compose.runtime.Composable

/**
 * Android: intercept system back.
 * iOS: no-op (there is no system back button).
 */
@Composable
expect fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit)
