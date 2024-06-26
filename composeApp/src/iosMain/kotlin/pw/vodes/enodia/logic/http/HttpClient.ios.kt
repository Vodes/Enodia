package pw.vodes.enodia.logic.http

import io.ktor.client.*
import io.ktor.client.engine.darwin.*

actual fun getNativeClient(block: HttpClientConfig<*>.() -> Unit): HttpClient {
    return HttpClient(Darwin) {
        block()
    }
}