package org.cufy.ktor_graphql_template.enumeration

import org.cufy.openperm.Permission
import org.cufy.openperm.SomePermission

// TODO: configure the permissions logic
//        Permissions are the consumable instances
//        that validates arbitrary conditions of
//        some document with a privilege

/**
 * The standard permissions.
 */
object Permissions {
    /**
     * Grants owner-level read access.
     */
    val OwnerRead = SomePermission(
        Permission(Permits.Owner)
    )

    /**
     * Grants read access.
     */
    val Read = SomePermission(
        Permission(Permits.Read),
        OwnerRead
    )

    /**
     * Grants anonymous read access.
     */
    val AnonymousRead = SomePermission(
        Permission(true),
        Read
    )

    /**
     * Grants owner-level write access.
     */
    val OwnerWrite = SomePermission(
        Permission(Permits.Owner)
    )

    /**
     * Grants write access.
     */
    val Write = SomePermission(
        Permission(Permits.Write),
        OwnerWrite
    )

    /**
     * Grants anonymous write access.
     */
    val AnonymousWrite = SomePermission(
        Permission(true),
        Write
    )
}
