package org.re.kmplittlelemon.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class SimpleNavController(start: Screen) {
    var current by mutableStateOf(start)
        private set

    fun navigate(to: Screen) { current = to }

    fun popBackStack() {
        // only needed for Profile -> Home
        if (current == Screen.Profile) current = Screen.Home
    }

    fun reset(to: Screen) { current = to }
}

@Composable
fun rememberNavController(start: Screen): SimpleNavController =
    remember { SimpleNavController(start) }