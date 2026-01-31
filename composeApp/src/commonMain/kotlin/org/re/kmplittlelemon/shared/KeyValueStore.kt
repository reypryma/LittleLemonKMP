package org.re.kmplittlelemon

import androidx.compose.runtime.Composable

/**
 * Very small cross-platform key-value storage.
 *
 * Android: SharedPreferences
 * iOS: NSUserDefaults
 */

interface KeyValueStore {
    fun putString(key: String, value: String?)
    fun getString(key: String): String?
    fun remove(key: String)
    fun clear()
}
@Composable
expect fun rememberKeyValueStore(): KeyValueStore
