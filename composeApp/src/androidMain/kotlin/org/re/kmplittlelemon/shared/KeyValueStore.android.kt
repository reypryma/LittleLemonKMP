package org.re.kmplittlelemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import android.content.Context // Unresolved reference 'content'.
import org.re.kmplittlelemon.KeyValueStore

// composeApp/src/androidMain/kotlin/org/re/kmplittlelemon/shared/KeyValueStore.android.kt
@Composable
actual fun rememberKeyValueStore(): KeyValueStore {
    val context = LocalContext.current.applicationContext // Cannot access class 'Context'. Check your module classpath for missing or conflicting dependencies.
    return remember { AndroidKeyValueStore(context) }
}

private class AndroidKeyValueStore(ctx: Context) : KeyValueStore { // Unresolved reference 'Context'.
    private val prefs = ctx.getSharedPreferences("littlelemon", Context.MODE_PRIVATE)

    override fun putString(key: String, value: String?) {
        prefs.edit().putString(key, value).apply()
    }

    override fun getString(key: String): String? = prefs.getString(key, null)

    override fun remove(key: String) {
        prefs.edit().remove(key).apply()
    }

    override fun clear() {
        prefs.edit().clear().apply()
    }

}