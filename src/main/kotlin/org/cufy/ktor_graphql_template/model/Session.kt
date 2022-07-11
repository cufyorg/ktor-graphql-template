package org.cufy.ktor_graphql_template.model

import kotlinx.serialization.Serializable

/*
This file is to demonstrate how server-runtime-only
classes might be implemented.
*/

/* =============== Definition    =============== */

/**
 * The class defining the shape of the
 * client-side session.
 */
@Serializable
data class Session(
    val read: MutableList<String> = mutableListOf(),
    val write: MutableList<String> = mutableListOf(),
    val owner: MutableList<String> = mutableListOf(),
)
