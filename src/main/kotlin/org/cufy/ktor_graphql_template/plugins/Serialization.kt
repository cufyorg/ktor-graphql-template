package org.cufy.ktor_graphql_template.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.doublereceive.*

/**
 * Configure application serialization plugins.
 *
 * This includes:
 * - Content Negotiation
 */
fun Application.configureSerialization() {
    install(DoubleReceive)
    install(ContentNegotiation) {
        // TODO: Configure serialization here
        json()
    }
}
