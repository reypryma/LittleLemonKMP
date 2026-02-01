package org.re.kmplittlelemon.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun rememberDatabase(): AppDatabase {
    val context = LocalContext.current
    return remember {
        getRoomDatabase(getDatabaseBuilder(context))
    }
}