plugins {
    application
    kotlin("jvm") version "1.7.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.7.10"
}

// TODO: change your application group
group = "org.cufy.ktor_graphql_template"
version = "0.0.1"

application {
    // TODO: change your application main class name
    mainClass.set("org.cufy.ktor_graphql_template.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    // TODO: Manage your dependencies here

    implementation(Dependencies.Ktor.core_jvm)
    implementation(Dependencies.Ktor.auth_jvm)
    implementation(Dependencies.Ktor.sessions_jvm)
    implementation(Dependencies.Ktor.content_negotiation_jvm)
    implementation(Dependencies.Ktor.cors_jvm)
    implementation(Dependencies.Ktor.default_headers_jvm)
    implementation(Dependencies.Ktor.call_logging_jvm)
    implementation(Dependencies.Ktor.serialization_kotlinx_json_jvm)
    implementation(Dependencies.Ktor.status_pages)
    implementation(Dependencies.Ktor.netty_jvm)
    implementation(Dependencies.Ktor.double_receive_jvm)

    implementation(Dependencies.GraphQL.graphql)
    implementation(Dependencies.GraphQL.kaguya)

    implementation(Dependencies.MongoDB.kmongo)
    implementation(Dependencies.MongoDB.kmongo_coroutine)
    implementation(Dependencies.MongoDB.mangaka)

    implementation(Dependencies.Crypto.bcrypt)
    implementation(Dependencies.Crypto.bcrypt_kt)
    implementation(Dependencies.Crypto.tink)

    implementation(Dependencies.dotenv)
    implementation(Dependencies.openperm)
    implementation(Dependencies.cufy_ktor_commons)
    implementation(Dependencies.logback_classic)

    testImplementation(Dependencies.Ktor.server_tests_jvm)
    testImplementation(Dependencies.Kotlin.test_junit)
}
