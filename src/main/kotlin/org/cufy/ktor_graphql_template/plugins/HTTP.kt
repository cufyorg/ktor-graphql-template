package org.cufy.ktor_graphql_template.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.defaultheaders.*

/**
 * Configure application protocol plugins.
 *
 * This includes:
 * - Standard http features (e.g. Compression and Caching)
 * - Protocol headers (e.g. CORS)
 * - Custom headers (e.g. X-Engine)
 * - Security Enforcement (e.g. redirect when not secure)
 */
fun Application.configureHTTP() {
    install(CORS) {
        // TODO: Configure CORS here
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Post)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.Cookie)
        allowNonSimpleContentTypes = true
//        anyHost()
    }
    install(DefaultHeaders) {
        // TODO: Disable X-Engine header
        header("X-Engine", "Ktor")
    }
}
