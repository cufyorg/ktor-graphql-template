package org.cufy.ktor_graphql_template

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.cufy.ktor_graphql_template.plugins.configureRouting
import kotlin.test.Test
import kotlin.test.assertEquals

// TODO: unit test your application here

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
