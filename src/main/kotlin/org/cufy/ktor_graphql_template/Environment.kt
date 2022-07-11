package org.cufy.ktor_graphql_template

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import com.google.crypto.tink.subtle.Hex as TinkHex
import com.google.crypto.tink.subtle.Random as TinkRandom

/**
 * Application environment variables.
 */
object Environment : Dotenv by (dotenv {
    ignoreIfMalformed = true
    ignoreIfMissing = true
}) {
    // TODO: configure your environment variables here

    /* System */

    /**
     * The port to listen at.
     */
    val Port = this["PORT"]?.toIntOrNull() ?: 3000

    /**
     * The domain of the server.
     */
    val Domain = this["DOMAIN"] ?: "localhost"

    /* Database */

    /**
     * The uri to the database.
     */
    val DatabaseUri = this["DB_URI"] ?: "mongodb://localhost:27017/"

    /**
     * The name of the database.
     */
    // TODO change to your database name
    val DatabaseName = this["DB_NAME"] ?: "Ktor-GraphQL-Template"

    /* Session */

    /**
     * The secret to sign/verify sessions with.
     */
    val SessionSignSecret = this["SESSION_SIGN_SECRET"]
        ?: TinkHex.encode(TinkRandom.randBytes(16))!!

    /**
     * The secret to encrypt/decrypt sessions with.
     */
    val SessionEncryptSecret = this["SESSION_ENCRYPT_SECRET"]
        ?: TinkHex.encode(TinkRandom.randBytes(16))!!

    /**
     * The maximum age of sessions.
     */
    val SessionMaxAge = this["SESSION_MAX_AGE"]?.toLongOrNull() ?: 604800000

    /* Graphql */

    /**
     * If the graphql playground is enabled or not.
     */
    val GraphqlPlayground = this["GRAPHIQL"]?.toBooleanStrictOrNull() ?: true
}
