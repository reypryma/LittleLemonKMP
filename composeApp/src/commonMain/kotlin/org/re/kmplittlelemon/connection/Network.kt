package org.re.kmplittlelemon.connection

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private const val MENU_URL =
    "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

@Serializable
data class MenuNetworkData(
    @SerialName("menu") val menu: List<MenuItemNetworkData>
)

@Serializable
data class MenuItemNetworkData(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

suspend fun fetchMenu(client: HttpClient): List<MenuItemNetworkData> {
    val result: MenuNetworkData = client.get(MENU_URL).body()
    return result.menu
}
