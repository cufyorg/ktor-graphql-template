package org.cufy.ktor_graphql_template.model

import graphql.schema.GraphQLNonNull
import io.ktor.server.sessions.*
import org.cufy.kaguya.GraphQLBoolean
import org.cufy.kaguya.GraphQLObjectTypeBuilder
import org.cufy.kaguya.field
import org.cufy.kaguya.ktor.call
import org.cufy.kaguya.resolver
import org.cufy.ktor.plugins.privilege
import org.cufy.ktor_graphql_template.enumeration.Permissions
import org.cufy.mangaka.Document
import org.cufy.mangaka._id
import org.cufy.mangaka.schema.ObjectSchemaBuilder
import org.cufy.openperm.testPermission

/*
This file is to demonstrate how interfaces might
be implemented.
 */


/* =============== Definition    =============== */

/**
 * An interface for documents that has access control.
 */
interface Accessible : Document

/* ================ MongoDB     ================ */

fun ObjectSchemaBuilder<out Accessible>.accessibleSchema() {
}

/* ================ GraphQL     ================ */

fun GraphQLObjectTypeBuilder<out Accessible>.accessibleObjectType() {
    field("canRead") {
        type = GraphQLNonNull(GraphQLBoolean)
        description = "Check if the caller has read access for this."

        resolver { testPermission(Permissions.Read, call.privilege, it) }
    }
    field("canWrite") {
        type = GraphQLNonNull(GraphQLBoolean)
        description = "Check if the caller has write access for this."

        resolver { testPermission(Permissions.Write, call.privilege, it) }
    }
    field("canOwnerRead") {
        type = GraphQLNonNull(GraphQLBoolean)
        description = "Check if the caller has owner read access for this."

        resolver { testPermission(Permissions.OwnerRead, call.privilege, it) }
    }
    field("canOwnerWrite") {
        type = GraphQLNonNull(GraphQLBoolean)
        description = "Check if the caller has owner write access for this."

        resolver { testPermission(Permissions.OwnerWrite, call.privilege, it) }
    }
}

fun GraphQLObjectTypeBuilder<out Accessible>.accessibleMutationObjectType() {
    field("obtainRead") {
        type = GraphQLBoolean
        description = "Obtain read access for this."

        resolver {
            val session = call.sessions.get<Session>()!!
            session.read += it._id.value
            true
        }
    }
    field("obtainWrite") {
        type = GraphQLBoolean
        description = "Obtain write access for this."

        resolver {
            val session = call.sessions.get<Session>()!!
            session.write += it._id.value
            true
        }
    }
    field("obtainOwner") {
        type = GraphQLBoolean
        description = "Obtain owner access for this."

        resolver {
            val session = call.sessions.get<Session>()!!
            session.owner += it._id.value
            true
        }
    }
}
