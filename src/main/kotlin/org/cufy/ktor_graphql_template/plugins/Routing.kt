package org.cufy.ktor_graphql_template.plugins

import io.ktor.http.HttpStatusCode.Companion.BadRequest
import io.ktor.http.HttpStatusCode.Companion.Forbidden
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.cufy.kaguya.ktor.context
import org.cufy.kaguya.ktor.graphql
import org.cufy.kaguya.ktor.instance
import org.cufy.kaguya.ktor.schema
import org.cufy.kaguya.mutation
import org.cufy.kaguya.onException
import org.cufy.kaguya.query
import org.cufy.ktor_graphql_template.Environment
import org.cufy.ktor_graphql_template.routes.entityMutationRoute
import org.cufy.ktor_graphql_template.routes.entityQueryRoute
import org.cufy.ktor_graphql_template.routes.helloWorldRoute
import org.cufy.mangaka.MangakaInvalidation
import org.cufy.openperm.Denial

/**
 * Configure application routing plugins.
 *
 * This includes:
 * - REST routing
 * - GraphQL routing
 * - Static routing
 * - error handling
 */
fun Application.configureRouting() {
    install(StatusPages) {
        // TODO: Customize REST exception handling here
        exception<MissingRequestParameterException> { call, cause ->
            call.respond(BadRequest, cause.message ?: "Missing request parameter.")
        }
        exception<NotFoundException> { call, cause ->
            call.respond(NotFound, cause.message ?: "Resource not found.")
        }
        exception<Denial> { call, cause ->
            call.respond(Forbidden, cause.message ?: "Access Denied.")
        }
        exception<MangakaInvalidation> { call, cause ->
            call.respond(BadRequest, cause.message ?: "Bad Request.")
        }
    }

    routing {
        // TODO: Define REST routing here
        helloWorldRoute()
    }

    graphql {
        graphiql = Environment.GraphqlPlayground

        context {
            // TODO: Add graphql context variables here
        }
        instance {
            // TODO: Customize graphql behaviour here

            onException {
                // TODO: Customize graphql exception handling here
                when (val cause = it.exception.cause) {
                    is Denial -> extensions(mapOf(
                        "code" to 403,
                        "message" to cause.message
                    ))
                    is MangakaInvalidation -> extensions(mapOf(
                        "code" to 400,
                        "message" to (cause.message
                            ?.split(":", limit = 2)
                            ?.getOrNull(1)
                            ?.trim()
                            ?: cause.message)
                    ))
                }
            }
        }
        schema {
            // TODO: Define the graphql schema here

            query {
                entityQueryRoute()
            }
            mutation {
                entityMutationRoute()
            }
        }
    }
}
