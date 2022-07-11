package org.cufy.ktor_graphql_template.enumeration

import org.cufy.mangaka._id
import org.cufy.mangaka.model
import org.cufy.openperm.Denial
import org.cufy.openperm.Permit

// TODO: Configure permits here
//        Permits are role factories that creates
//        roles from documents.

/**
 * The standard permits.
 */
object Permits {
    val Read = Permit {
        Permit(ReadRole(
            document = it,
            error = Denial("Denial: 403 READ ${it.model.name} ${it._id}")
        ))
    }

    val Write = Permit {
        Permit(WriteRole(
            document = it,
            error = Denial("Denial: 403 WRITE ${it.model.name} ${it._id.value}")
        ))
    }

    val Owner = Permit {
        Permit(OwnerRole(
            document = it,
            error = Denial("Denial: 403 OWNER ${it.model.name} ${it._id.value}")
        ))
    }
}
