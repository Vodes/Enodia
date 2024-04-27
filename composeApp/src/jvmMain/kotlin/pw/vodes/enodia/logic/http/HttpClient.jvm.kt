package pw.vodes.enodia.logic.http

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

actual fun getNativeClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(OkHttp) {
        block()
    }
}