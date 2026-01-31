package org.re.kmplittlelemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.re.kmplittlelemon.data.UserPrefsKeys
import org.re.kmplittlelemon.nav.Screen
import org.re.kmplittlelemon.nav.rememberNavController
import org.re.kmplittlelemon.screens.HomeScreen
import org.re.kmplittlelemon.screens.OnboardingScreen
import org.re.kmplittlelemon.screens.ProfileScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        val store = rememberKeyValueStore()

        val startScreen = remember {
            val loggedIn = store.getString(UserPrefsKeys.LOGGED_IN) == "true"
            if (loggedIn) Screen.Home else Screen.Onboarding
        }

        val navController = rememberNavController(startScreen)

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (navController.current) {
                Screen.Onboarding -> {
                    OnboardingScreen { first, last, email ->
                        // save to shared prefs
                        store.putString(UserPrefsKeys.FIRST_NAME, first)
                        store.putString(UserPrefsKeys.LAST_NAME, last)
                        store.putString(UserPrefsKeys.EMAIL, email)
                        store.putString(UserPrefsKeys.LOGGED_IN, "true")

                        navController.reset(Screen.Home)
                    }
                }

                Screen.Home -> {
                    HomeScreen(
                        onOpenProfile = { navController.navigate(Screen.Profile) }
                    )
                }

                Screen.Profile -> {
                    ProfileScreen(
                        store = store,
                        onBack = { navController.popBackStack() },
                        onLogout = {
                            // store.clear() already called inside ProfileScreen
                            navController.reset(Screen.Onboarding)
                        }
                    )
                }
            }
        }
    }
}
