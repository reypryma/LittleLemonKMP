package org.re.kmplittlelemon.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.re.kmplittlelemon.network.MenuNetwork

class MenuRepository(
    private val dao: MenuItemDao,
    private val client: HttpClient,
) {
    private val url =
        "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

    suspend fun refreshIfEmpty() {
        if (dao.count() > 0) return

        val network: MenuNetwork = client.get(url).body()
        val entities = network.menu.map { n ->
            MenuItemEntity(
                id = n.id,
                title = n.title,
                description = n.description,
                price = n.price.toDoubleOrNull() ?: 0.0,
                image = n.image,
                category = n.category
            )
        }
        dao.upsertAll(entities)
    }
}