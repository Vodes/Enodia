package pw.vodes.enodia.logic.http

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import pw.vodes.enodia.logic.json

val httpClient by lazy {
    getNativeClient {
        install(UserAgent) {
            agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/121.0.0.0 Safari/537.36"
        }
        install(ContentNegotiation) {
            json
        }
        install(ContentEncoding)
        install(HttpCookies)
    }
}

expect fun getNativeClient(block: HttpClientConfig<*>.() -> Unit = {}): HttpClient