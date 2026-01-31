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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import littlelemonkmp.composeapp.generated.resources.Res
import littlelemonkmp.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onRegister: (firstName: String, lastName: String, email: String) -> Unit = { _, _, _ -> }
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val canRegister = firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()

    Column(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Header (logo)
        Image(
            painter = painterResource(Res.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier.size(180.dp)
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Let's get to know you",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(20.dp))

        TextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        TextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )

        Button(
            onClick = { onRegister(firstName.trim(), lastName.trim(), email.trim()) },
            enabled = canRegister,
        ) {
            Text("Register")
        }
    }
}

@Preview
@Composable
private fun OnboardingScreenPreview() {
    MaterialTheme {
        OnboardingScreen()
    }
}
