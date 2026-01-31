package org.re.kmplittlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import littlelemonkmp.composeapp.generated.resources.Res
import littlelemonkmp.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.re.kmplittlelemon.KeyValueStore
import org.re.kmplittlelemon.data.UserPrefsKeys
import org.re.kmplittlelemon.nav.User

@Composable
fun ProfileScreen(
    store: KeyValueStore,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onLogout: () -> Unit,
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // load once
    LaunchedEffect(Unit) {
        firstName = store.getString(UserPrefsKeys.FIRST_NAME).orEmpty()
        lastName  = store.getString(UserPrefsKeys.LAST_NAME).orEmpty()
        email     = store.getString(UserPrefsKeys.EMAIL).orEmpty()
    }

    Column(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Header (logo)
        Image(
            painter = painterResource(Res.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier.size(160.dp)
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Profile information:",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "First name: $firstName")
            Spacer(Modifier.height(8.dp))
            Text(text = "Last name: $lastName")
            Spacer(Modifier.height(8.dp))
            Text(text = "Email: $email")
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }

        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                store.clear()
                onLogout()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log out")
        }
    }
}

@Preview
@Composable
private fun ProfileScreenPreview() {
    // preview dummy store (in-memory)
    val dummy = object : KeyValueStore {
        private val map = mutableMapOf(
            UserPrefsKeys.FIRST_NAME to "Jane",
            UserPrefsKeys.LAST_NAME to "Doe",
            UserPrefsKeys.EMAIL to "jane@littlelemon.com",
        )
        override fun putString(key: String, value: String?) { if (value == null) map.remove(key) else map[key] = value }
        override fun getString(key: String): String? = map[key]
        override fun remove(key: String) { map.remove(key) }
        override fun clear() { map.clear() }
    }

    MaterialTheme {
        ProfileScreen(store = dummy, onBack = {}, onLogout = {})
    }
}
