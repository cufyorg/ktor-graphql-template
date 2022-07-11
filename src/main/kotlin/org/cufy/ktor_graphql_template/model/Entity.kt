package org.cufy.ktor_graphql_template.model

import graphql.schema.GraphQLNonNull
import kotlinx.serialization.Serializable
import org.cufy.kaguya.*
import org.cufy.kaguya.ktor.call
import org.cufy.ktor.plugins.privilege
import org.cufy.ktor.plugins.saveQueue
import org.cufy.ktor_graphql_template.enumeration.Permissions
import org.cufy.mangaka.Document
import org.cufy.mangaka.Mangaka.Companion.model
import org.cufy.mangaka._id
import org.cufy.mangaka.schema.ObjectSchema
import org.cufy.mangaka.schema.extension.insure
import org.cufy.mangaka.schema.extension.required
import org.cufy.mangaka.schema.field
import org.cufy.mangaka.schema.schema
import org.cufy.mangaka.schema.types.Int64Schema
import org.cufy.mangaka.schema.types.StringSchema
import org.cufy.mangaka.validate
import org.cufy.openperm.requirePermission

/*
This file is to demonstrate how classes might
be implemented.

A class definition file is split into 3 parts

### Kotlin definitions (Server)
In this part, you might define the kotlin classes,
interfaces and enums.

This part defines the data available at server runtime.

### MongoDB definitions (Server/Database)
In this part, you might define the Mangaka schemas.

This part defines how data is stored-to/loaded-from
mongodb.

### GraphQL definitions (Server/Client)
In this part, you might define the GraphQL object
types.

This part defines the shape of data available at
the graphql endpoint.
 */

/* =============== Definition    =============== */

@Serializable
class Entity : Accessible, Document {
    var age: Long = 0L
    lateinit var name: String
}

/* ================ MongoDB     ================ */

val EntitySchema = ObjectSchema(::Entity) {
    accessibleSchema()

    field(Entity::age) {
        schema { Int64Schema }
        required()
    }
    field(Entity::name) {
        schema { StringSchema }
        required()
        insure({ "Name is too short" }) { it.length >= 3 }
    }
}

val EntityModel = model("Entity", EntitySchema, "Entity")

/* ================ GraphQL     ================ */

val EntityObjectType = GraphQLObjectType<Entity>("Entity") {
    description = "An entity definition."

    accessibleObjectType()

    field("id") {
        type = GraphQLNonNull(GraphQLString)
        description = "The id of the entity."
        resolver { it._id.value }
    }
    field(Entity::name) {
        type = GraphQLNonNull(GraphQLString)
        description = "The name of the entity."
    }
    field(Entity::age) {
        type = GraphQLNonNull(GraphQLLong)
        description = """
            The age of the entity.
            
            Requires Permission: Read
        """.trimIndent()

        resolver {
            requirePermission(Permissions.Read, call.privilege, it)
            it.age
        }
    }
}

val EntityMutationObjectType = GraphQLObjectType<Entity>("EntityMutation") {
    description = "A mutation instance of an entity."

    accessibleMutationObjectType()

    field("setName") {
        type = GraphQLNonNull(GraphQLBoolean)
        description = """
            Set the name of the entity.
            
            Requires Permission: Write
        """.trimIndent()

        val nameArg = argument<String>("name") {
            type = GraphQLNonNull(GraphQLString)
            description = "The new name of the entity."
        }

        resolver {
            requirePermission(Permissions.Write, call.privilege, it)
            it.name = nameArg()
            it.validate()
            call.saveQueue += it
            true
        }
    }
    field("setAge") {
        type = GraphQLNonNull(GraphQLBoolean)
        description = """
            Set the age of the entity.
            
            Requires Permission: OwnerWrite
        """.trimIndent()

        val ageArg = argument<Long>("age") {
            type = GraphQLNonNull(GraphQLLong)
            description = "The new age of the entity."
        }

        resolver {
            requirePermission(Permissions.OwnerWrite, call.privilege, it)
            it.age = ageArg()
            it.validate()
            call.saveQueue += it
            true
        }
    }
}
