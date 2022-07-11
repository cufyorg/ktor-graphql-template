package org.cufy.ktor_graphql_template

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.cufy.ktor_graphql_template.plugins.*

fun main() {
    // TODO: your server starts here
    embeddedServer(Netty, Environment.Port, Environment.Domain) {
        configureDatabase()
        configureHTTP()
        configureMonitoring()
        configureRouting()
        configureSecurity()
        configureSerialization()
    }.start(wait = true)
}
