package org.cufy.ktor_graphql_template.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*

/*
This file is to demonstrate how REST routing
might be implemented.
 */

@KtorDsl
fun Route.helloWorldRoute() {
    get("/") {
        call.respond("Hello World!")
    }
}
