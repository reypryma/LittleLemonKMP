package org.re.kmplittlelemon.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import littlelemonkmp.composeapp.generated.resources.Res
import littlelemonkmp.composeapp.generated.resources.hero_image
import littlelemonkmp.composeapp.generated.resources.logo
import littlelemonkmp.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.painterResource
import org.re.kmplittlelemon.data.MenuItemEntity
import kotlin.math.round

@Composable
fun HomeScreen(
    menuItems: List<MenuItemEntity>,
    modifier: Modifier = Modifier,
    onOpenProfile: () -> Unit,
) {
    var searchPhrase by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) } // null = All

    val categories = remember(menuItems) {
        menuItems.map { it.category }.distinct().sorted()
    }

    val filteredItems = remember(menuItems, searchPhrase, selectedCategory) {
        menuItems
            .asSequence()
            .filter { selectedCategory == null || it.category.equals(selectedCategory, ignoreCase = true) }
            .filter {
                searchPhrase.isBlank() ||
                        it.title.contains(searchPhrase, ignoreCase = true) ||
                        it.description.contains(searchPhrase, ignoreCase = true)
            }
            .toList()
    }

    LazyColumn(
        modifier = modifier
            .safeContentPadding()
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {

        // ✅ Header (logo center, profile right)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(Modifier.size(40.dp))

                Image(
                    painter = painterResource(Res.drawable.logo),
                    contentDescription = "Little Lemon logo",
                    modifier = Modifier.size(40.dp)
                )

                Image(
                    painter = painterResource(Res.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onOpenProfile() }
                )
            }
        }

        // ✅ Hero + Search
        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Little Lemon",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text("Chicago", style = MaterialTheme.typography.headlineSmall)

                    Spacer(Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(Modifier.width(12.dp))

                        Image(
                            painter = painterResource(Res.drawable.hero_image),
                            contentDescription = "Hero image",
                            modifier = Modifier
                                .size(110.dp)
                                .clip(RoundedCornerShape(12.dp))
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    TextField(
                        value = searchPhrase,
                        onValueChange = { searchPhrase = it },
                        placeholder = { Text("Enter search phrase") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(Modifier.height(12.dp))
        }

        // ✅ Menu breakdown chips
        item {
            Text(
                "Menu breakdown",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(Modifier.height(8.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    FilterChip(
                        selected = selectedCategory == null,
                        onClick = { selectedCategory = null },
                        label = { Text("All") }
                    )
                }
                items(categories) { cat ->
                    FilterChip(
                        selected = selectedCategory.equals(cat, ignoreCase = true),
                        onClick = {
                            selectedCategory =
                                if (selectedCategory.equals(cat, ignoreCase = true)) null else cat
                        },
                        label = { Text(cat.replaceFirstChar { it.uppercase() }) }
                    )
                }
            }

            Spacer(Modifier.height(12.dp))
        }

        // ✅ Menu items list
        items(filteredItems, key = { it.id }) { item ->
            MenuItemRow(item)
            Divider()
        }
    }
}

@Composable
private fun MenuItemRow(item: MenuItemEntity) {
    val formattedPrice = (round(item.price * 100) / 100).toString()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                item.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(item.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                "$$formattedPrice",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(Modifier.width(12.dp))

        AsyncImage(
            model = item.image,
            contentDescription = item.title,
            modifier = Modifier
                .size(90.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}