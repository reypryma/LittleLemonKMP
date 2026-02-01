package org.re.kmplittlelemon.connection

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(): HttpClient =
    HttpClient {
        install(ContentNegotiation) {
            // normal JSON servers
            json(
                json = Json { ignoreUnknownKeys = true; isLenient = true },
                contentType = ContentType.Application.Json
            )
            // GitHub raw returns JSON as text/plain; charset=utf-8
            json(
                json = Json { ignoreUnknownKeys = true; isLenient = true },
                contentType = ContentType.Text.Plain
            )
        }

        defaultRequest {
            accept(ContentType.Application.Json)
        }
    }