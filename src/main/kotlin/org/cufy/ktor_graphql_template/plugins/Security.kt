package org.cufy.ktor_graphql_template.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.sessions.*
import io.ktor.util.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.cufy.ktor.plugins.privilege
import org.cufy.ktor_graphql_template.Environment
import org.cufy.ktor_graphql_template.enumeration.SessionPrivilege
import org.cufy.ktor_graphql_template.model.Session
import org.cufy.openperm.CachedPrivilege
import kotlin.time.Duration.Companion.milliseconds

/**
 * Configure application security plugins.
 *
 * This includes:
 * - Authentication
 * - Authorization
 */
fun Application.configureSecurity() {
    install(Sessions) {
        // TODO: change session name
        cookie<Session>("org.cufy.ktor_graphql_template") {
            cookie.path = "/"
            cookie.domain = Environment.Domain
            cookie.encoding = CookieEncoding.BASE64_ENCODING
            cookie.httpOnly = true
            cookie.extensions["SameSite"] = "lax"
            cookie.maxAge = Environment.SessionMaxAge.milliseconds
            serializer = object : SessionSerializer<Session> {
                override fun deserialize(text: String): Session =
                    Json.decodeFromString(text)

                override fun serialize(session: Session): String =
                    Json.encodeToString(session)
            }
            transform(SessionTransportTransformerEncrypt(
                hex(Environment.SessionEncryptSecret),
                hex(Environment.SessionSignSecret)
            ))
        }
    }
    install(createApplicationPlugin("Privilege") {
        onCall { call ->
            // TODO: configure privilege/session setup here
            val session = call.sessions.getOrSet {
                Session()
            }

            call.privilege = CachedPrivilege(
                SessionPrivilege(session)
            )
        }
    })
}
