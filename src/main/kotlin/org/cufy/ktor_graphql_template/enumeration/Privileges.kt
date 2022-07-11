package org.cufy.ktor_graphql_template.enumeration

import org.cufy.ktor_graphql_template.model.Session
import org.cufy.mangaka._id
import org.cufy.openperm.Privilege

// TODO: Configure the privilege implementation here
//        The privilege is the code to determine if
//        A role is met or not.

/**
 * Construct a new session privilege.
 */
fun SessionPrivilege(session: Session) = Privilege { role ->
    when (role) {
        is ReadRole -> Privilege(role.document._id.value in session.read)
        is WriteRole -> Privilege(role.document._id.value in session.write)
        is OwnerRole -> Privilege(role.document._id.value in session.owner)
        else -> Privilege(false)
    }
}
