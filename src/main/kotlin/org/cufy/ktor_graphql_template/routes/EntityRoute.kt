package org.cufy.ktor_graphql_template.routes

import graphql.schema.GraphQLList
import graphql.schema.GraphQLNonNull
import io.ktor.server.sessions.*
import io.ktor.util.*
import org.cufy.kaguya.*
import org.cufy.kaguya.ktor.call
import org.cufy.ktor_graphql_template.model.EntityModel
import org.cufy.ktor_graphql_template.model.EntityMutationObjectType
import org.cufy.ktor_graphql_template.model.EntityObjectType
import org.cufy.ktor_graphql_template.model.Session
import org.cufy.mangaka.*

/*
This file is to demonstrate how graphql routing
might be implemented.
 */

@KtorDsl
fun GraphQLObjectTypeBuilder<*>.entityQueryRoute() {
    field("getEntityById") {
        type = EntityObjectType
        description = "Get an entity by its id."

        val idArg = argument<String>("id") {
            type = GraphQLNonNull(GraphQLString)
            description = "The id of the entity."
        }

        resolver {
            EntityModel.findOneById(Id(idArg()))
        }
    }
    field("getEntities") {
        type = GraphQLList(GraphQLNonNull(EntityObjectType))
        description = "Get all available entities."

        resolver {
            EntityModel.find()
        }
    }
}

@KtorDsl
fun GraphQLObjectTypeBuilder<*>.entityMutationRoute() {
    field("createEntity") {
        type = GraphQLBoolean
        description = "Create a new entity."

        val nameArg = argument<String>("name") {
            type = GraphQLNonNull(GraphQLString)
            description = "The name of the new entity."
        }
        val ageArg = argument<Long>("age") {
            type = GraphQLNonNull(GraphQLLong)
            description = "The age of the new entity."
        }

        resolver {
            val entity = EntityModel()
            entity.name = nameArg()
            entity.age = ageArg()
            entity.save()
            val session = call.sessions.get<Session>()!!
            session.owner += entity._id.value
            true
        }
    }
    field("mutateEntityById") {
        type = EntityMutationObjectType
        description = "Mutate an entity by its id."

        val idArg = argument<String>("id") {
            type = GraphQLNonNull(GraphQLString)
            description = "The id of the entity."
        }

        resolver {
            EntityModel.findOneById(Id(idArg()))
        }
    }
}
