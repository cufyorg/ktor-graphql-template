package org.cufy.ktor_graphql_template.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import org.slf4j.event.Level

/**
 * Configure application monitoring plugins.
 *
 * This includes:
 * - Logging
 * - Benchmarks
 * - Error Reporting
 */
fun Application.configureMonitoring() {
    install(CallLogging) {
        // TODO: Configuration logging
        level = Level.INFO
//        filter { call -> call.request.path().startsWith("/") }
    }
}
