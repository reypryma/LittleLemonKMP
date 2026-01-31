package org.re.kmplittlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import littlelemonkmp.composeapp.generated.resources.Res
import littlelemonkmp.composeapp.generated.resources.logo
import littlelemonkmp.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onOpenProfile: () -> Unit,
) {
    Column(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Header row: profile icon on the right
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(Res.drawable.profile),
                contentDescription = "Open profile",
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterEnd)
                    .clickable(onClick = onOpenProfile)
            )
        }

        Spacer(Modifier.height(24.dp))

        // Center logo
        Image(
            painter = painterResource(Res.drawable.logo),
            contentDescription = "Little Lemon logo",
            modifier = Modifier.size(160.dp)
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Home",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Welcome to Little Lemon!",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MaterialTheme {
        HomeScreen(onOpenProfile = {})
    }
}
