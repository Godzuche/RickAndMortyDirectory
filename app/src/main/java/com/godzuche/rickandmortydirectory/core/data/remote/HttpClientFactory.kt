package com.godzuche.rickandmortydirectory.core.data.remote

import com.godzuche.rickandmortydirectory.core.data.remote.util.DEV_HTTP_TIMEOUT
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpClientFactory {
    fun create(
        engine: HttpClientEngine,
    ): HttpClient {
        return HttpClient(engine) {
            expectSuccess = false // disable exceptions thrown for non-2xx error responses
            install(ContentNegotiation) {
                json(
                    Json {
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(HttpTimeout) {
                socketTimeoutMillis = DEV_HTTP_TIMEOUT
                requestTimeoutMillis = DEV_HTTP_TIMEOUT
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println("HTTP Client: $message")
                    }
                }
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }

            install(HttpRequestRetry) {
                retryOnServerErrors(3)
                exponentialDelay()
            }

        }
    }
}
