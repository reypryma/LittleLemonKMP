package org.re.kmplittlelemon.nav

data class User(val firstName: String, val lastName: String, val email: String)

sealed interface Screen {
    data object Onboarding : Screen
    data object Home : Screen
    data object Profile : Screen
}