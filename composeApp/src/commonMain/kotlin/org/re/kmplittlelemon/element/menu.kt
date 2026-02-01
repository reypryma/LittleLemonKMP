package org.re.kmplittlelemon.element

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.re.kmplittlelemon.data.MenuItemEntity

@Composable
fun MenuItems(items: List<MenuItemEntity>) {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        items.forEach { item ->
            MenuItemRow(item)
            HorizontalDivider(Modifier, DividerDefaults.Thickness, DividerDefaults.color)
        }
    }
}

@Composable
fun MenuItemRow(item: MenuItemEntity) {
    Row(
        Modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f)) {
            Text(item.title, style = MaterialTheme.typography.titleLarge)
            Text("$${item.price}", style = MaterialTheme.typography.bodyLarge)
            Text(item.description, style = MaterialTheme.typography.bodyMedium)
        }

        AsyncImage(
            model = item.image,
            contentDescription = item.title,
            modifier = Modifier.size(80.dp)
        )
    }
}
