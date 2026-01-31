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
import org.re.kmplittlelemon.nav.Screen
import org.re.kmplittlelemon.nav.User
import org.re.kmplittlelemon.screens.HomeScreen

import org.re.kmplittlelemon.screens.OnboardingScreen
import org.re.kmplittlelemon.screens.ProfileScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        var user by remember { mutableStateOf<User?>(null) }
        var screen by remember { mutableStateOf<Screen>(Screen.Onboarding) }

        // derive what to show:
        val currentScreen: Screen = if (user == null) {
            Screen.Onboarding
        } else {
            // once registered, never show onboarding unless logout
            if (screen == Screen.Onboarding) Screen.Home else screen
        }

        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (currentScreen) {
                Screen.Onboarding -> {
                    OnboardingScreen { first, last, email ->
                        user = User(first, last, email)
                        screen = Screen.Home
                    }
                }

                Screen.Home -> {
                    HomeScreen(onOpenProfile = { })
                }

                Screen.Profile -> {
                    ProfileScreen(
                        user = user!!,
                        onBack = { screen = Screen.Home },
                        onLogout = {
                            user = null
                            screen = Screen.Onboarding
                        }
                    )
                }
            }
        }
    }
}