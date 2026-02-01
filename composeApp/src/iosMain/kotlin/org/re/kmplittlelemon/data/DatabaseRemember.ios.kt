package org.re.kmplittlelemon.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberDatabase(): AppDatabase {
    return remember {
        getRoomDatabase(getDatabaseBuilder())
    }
}