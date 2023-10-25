package com.fikrisandi.loginapp.framework.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

class AppHttpClient @Inject constructor() {
    operator fun invoke() = HttpClient(OkHttp) {
        engine {
            this.config {
                readTimeout(60_000, TimeUnit.MILLISECONDS)
                retryOnConnectionFailure(true)
                callTimeout(60_000, TimeUnit.MILLISECONDS)

            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                setLenient()
                disableHtmlEscaping()
            }
        }
    }
}