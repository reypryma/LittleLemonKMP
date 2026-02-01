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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import littlelemonkmp.composeapp.generated.resources.Res
import littlelemonkmp.composeapp.generated.resources.logo
import org.jetbrains.compose.resources.painterResource
import org.re.kmplittlelemon.ui.components.LLPrimaryButton
import org.re.kmplittlelemon.ui.components.LLTextField

private fun isValidEmail(input: String): Boolean {
    val email = input.trim()
    if (email.isEmpty()) return false
    // Simple, practical email validation (works well for onboarding forms)
    val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return regex.matches(email)
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onRegister: (firstName: String, lastName: String, email: String) -> Unit = { _, _, _ -> }
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Show email error only after user starts typing / leaves blank
    val emailTouched = remember(email) { email.isNotEmpty() }
    val emailValid = remember(email) { isValidEmail(email) }
    val emailErrorText = if (emailTouched && !emailValid) "Please enter a valid email address" else null

    val canRegister = firstName.isNotBlank() &&
            lastName.isNotBlank() &&
            emailValid

    Column(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
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

        LLTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = "First name",
        )

        Spacer(Modifier.height(12.dp))

        LLTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = "Last name",
        )

        Spacer(Modifier.height(12.dp))

        LLTextField(
            value = email,
            onValueChange = { email = it },
            label = "Email",
            placeholder = "name@example.com",
            isError = emailErrorText != null,
            supportingText = emailErrorText,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
        )

        Spacer(Modifier.height(20.dp))

        LLPrimaryButton(
            onClick = { onRegister(firstName.trim(), lastName.trim(), email.trim()) },
            enabled = canRegister,
            text = "Register",
            modifier = Modifier.fillMaxWidth()
        )
    }
}

