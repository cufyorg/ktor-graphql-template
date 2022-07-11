package org.cufy.ktor_graphql_template.enumeration

import org.cufy.ktor_graphql_template.model.Accessible
import org.cufy.openperm.Role

// TODO: Configure the kinds of roles that a privilege
//        can validate.

/**
 * A role to check if the privilege has read
 * rights to some document.
 */
data class ReadRole(
    /**
     * The target document.
     */
    val document: Accessible,

    //

    override val error: Throwable? = null
) : Role()

/**
 * A role to check if the privilege has write
 * rights to some document.
 */
data class WriteRole(
    /**
     * The target document.
     */
    val document: Accessible,

    //

    override val error: Throwable? = null
) : Role()

/**
 * A role to check if the privilege has owner
 * rights to some document.
 */
data class OwnerRole(
    /**
     * The target document.
     */
    val document: Accessible,

    //

    override val error: Throwable? = null
) : Role()
