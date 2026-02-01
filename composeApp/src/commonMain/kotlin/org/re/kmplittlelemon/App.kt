package org.re.kmplittlelemon

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import org.re.kmplittlelemon.connection.createHttpClient
import org.re.kmplittlelemon.data.MenuRepository
import org.re.kmplittlelemon.data.UserPrefsKeys
import org.re.kmplittlelemon.data.rememberDatabase
import org.re.kmplittlelemon.nav.Screen
import org.re.kmplittlelemon.nav.User
import org.re.kmplittlelemon.screens.HomeScreen
import org.re.kmplittlelemon.screens.OnboardingScreen
import org.re.kmplittlelemon.screens.ProfileScreen
import org.re.kmplittlelemon.ui.theme.LittleLemonTheme


@Composable
@Preview
fun App() {
    LittleLemonTheme {

            val db = rememberDatabase()
            val client = remember { createHttpClient() }
            val repo = remember { MenuRepository(db.menuItemDao(), client) }
            val store = rememberKeyValueStore()

            val menuItems by db.menuItemDao().getAll().collectAsState(emptyList())

            LaunchedEffect(Unit) { repo.refreshIfEmpty() }

            // ✅ read login status once (and keep in memory while app runs)
            var isLoggedIn by remember {
                mutableStateOf(store.getString(UserPrefsKeys.LOGGED_IN) == "true")
            }

            // ✅ only used when logged in (Home/Profile)
            var screen by remember { mutableStateOf<Screen>(Screen.Home) }

            val currentScreen: Screen = if (!isLoggedIn) Screen.Onboarding else screen

            when (currentScreen) {
                Screen.Onboarding -> {
                    OnboardingScreen { first, last, email ->
                        store.putString(UserPrefsKeys.FIRST_NAME, first)
                        store.putString(UserPrefsKeys.LAST_NAME, last)
                        store.putString(UserPrefsKeys.EMAIL, email)
                        store.putString(UserPrefsKeys.LOGGED_IN, "true")

                        isLoggedIn = true
                        screen = Screen.Home
                    }
                }

                Screen.Home -> {
                    HomeScreen(
                        menuItems = menuItems,
                        onOpenProfile = { screen = Screen.Profile }
                    )
                }

                Screen.Profile -> {
                    ProfileScreen(
                        store = store,
                        onBack = { screen = Screen.Home },
                        onLogout = {
                            store.clear()
                            isLoggedIn = false
                            screen = Screen.Home
                        }
                    )
                }
            }
        }

}
