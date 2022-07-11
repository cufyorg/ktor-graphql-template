val kotlin_version = "1.7.0"
val ktor_version = "2.0.3"

object Dependencies {
    // TODO: define your dependencies here

    object Ktor {
        val serialization_kotlinx_json_jvm =
            "io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version"
        val core_jvm = "io.ktor:ktor-server-core-jvm:$ktor_version"
        val auth_jvm = "io.ktor:ktor-server-auth-jvm:$ktor_version"
        val sessions_jvm = "io.ktor:ktor-server-sessions-jvm:$ktor_version"
        val double_receive_jvm = "io.ktor:ktor-server-double-receive-jvm:$ktor_version"
        val content_negotiation_jvm = "io.ktor:ktor-server-content-negotiation-jvm:$ktor_version"
        val cors_jvm = "io.ktor:ktor-server-cors-jvm:$ktor_version"
        val default_headers_jvm = "io.ktor:ktor-server-default-headers-jvm:$ktor_version"
        val call_logging_jvm = "io.ktor:ktor-server-call-logging-jvm:$ktor_version"
        val status_pages = "io.ktor:ktor-server-status-pages:$ktor_version"
        val netty_jvm = "io.ktor:ktor-server-netty-jvm:$ktor_version"
        val server_tests_jvm = "io.ktor:ktor-server-tests-jvm:$ktor_version"
    }

    object Kotlin {
        val test_junit = "org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version"
    }

    object GraphQL {
        val graphql = "com.graphql-java:graphql-java:18.1"
        val kaguya = "org.cufy:kaguya:1.0.0"
    }

    object MongoDB {
        val kmongo = "org.litote.kmongo:kmongo:4.6.0"
        val kmongo_coroutine = "org.litote.kmongo:kmongo-coroutine:4.6.0"
        val mangaka = "org.cufy:mangaka:1.0.0"
    }

    object Crypto {
        val bcrypt = "at.favre.lib:bcrypt:0.9.0"
        val bcrypt_kt = "net.lsafer:bcrypt-kt:1.0.1"
        val tink = "com.google.crypto.tink:tink:1.6.1"
    }

    val dotenv = "io.github.cdimascio:dotenv-kotlin:6.3.1"
    val weakness = "org.cufy:weakness:1.0.0"
    val openperm = "org.cufy:openperm-kt:1.0.0"
    val cufy_ktor_commons = "org.cufy:ktor-commons:1.0.0"
    val logback_classic = "ch.qos.logback:logback-classic:1.2.11"
}
