package org.re.kmplittlelemon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.Foundation.NSUserDefaults


private class IosKeyValueStore(
    private val defaults: NSUserDefaults
) : KeyValueStore {

    override fun putString(key: String, value: String?) {
        if (value == null) defaults.removeObjectForKey(key)
        else defaults.setObject(value, forKey = key)
    }

    override fun getString(key: String): String? = defaults.stringForKey(key)

    override fun remove(key: String) {
        defaults.removeObjectForKey(key)
    }

    override fun clear() {
        // simplest: clear only keys you use (recommended)
        remove("firstName")
        remove("lastName")
        remove("email")
        remove("loggedIn")
    }
}

@Composable
actual fun rememberKeyValueStore(): KeyValueStore {
    return remember { IosKeyValueStore(NSUserDefaults.standardUserDefaults) }
}