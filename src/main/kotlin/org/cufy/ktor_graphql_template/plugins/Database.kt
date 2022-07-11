package org.cufy.ktor_graphql_template.plugins

import io.ktor.server.application.*
import org.cufy.ktor.plugins.SaveQueue
import org.cufy.ktor_graphql_template.Environment
import org.cufy.mangaka.Mangaka

/**
 * Configure application database plugins.
 *
 * This includes:
 * - Establishing database connection.
 * - Database plugins.
 */
fun Application.configureDatabase() {
    // TODO: Database connection is here
    Mangaka.connect(Environment.DatabaseUri, Environment.DatabaseName)

    install(SaveQueue) {
        noValidate()
    }
}
