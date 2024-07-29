package data.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object PostAppClient {

    private lateinit var baseUrl: String

    fun host(baseUrl: String) = apply { this.baseUrl = baseUrl }

    fun build(): HttpClient {
        return HttpClient {

            defaultRequest {
                url(this@PostAppClient.baseUrl)
            }

            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
    }
}