# Ktor GraphQL Template

A template to use ktor with GraphQL and MongoDB
using `mangaka`, `kaguya` and `openperm-kt`

### Setup

To configure the library to the desired shape.
It is better to configure it in these steps.

#### Configure Environment

Change the available environment variables
at the `Environment` file.

#### Configure Plugins

Configure the plugins at the `plugins` package.

You might skip routing configuration until you configure
your routes later on the process.

You might skip session configuration until you configure
your session models, user models, role classes and privilege
factories.

#### Configure Models

Configure Runtime/GraphQL/MongoDB models at `model`
package

You might flag permission checks until you configure
your permissions.

#### Configure Enumerations

Configured project enums and data classes at `enumeration`
package

In this step you configure available roles, permissions,
permits and privilege factories and other project-wide
constants.

#### Configure Routing

Finally, you might define your REST routes and your
GraphQL query, mutation and subscription fields.

### Technologies Used

#### Server

- Ktor: The server/client framework
    - [Official Page](https://ktor.io/)

#### MongoDB

- Mongo Java Driver: The core mongodb driver
    - [Official Page](https://www.mongodb.com/docs/drivers/java-drivers/)

- KMongo: A mongodb library optimized for kotlin
    - [Official Page](https://litote.org/kmongo/)

- Mangaka: A mongodb library for schema validation.
    - [Official Page](https://github.com/cufyorg/mangaka)

#### GraphQL

- GraphQL-Java: The core graphql driver
    - [Official Page](https://www.graphql-java.com/)

- Kaguya: A graphql library optimized for kotlin
    - [Official Page](https://github.com/cufyorg/kaguya)

#### Crypto

- Tink: Main encryption library
    - [Official Page](https://github.com/google/tink)

- BCrypt: Hashing library
    - [Official Page](https://github.com/patrickfav/bcrypt)

- BCrypt-Kt: A bcrypt library optimized for kotlin
    - [Official Page](https://github.com/lsafer/bcrypt-kt)

#### Etc

- Dotenv: Environment variables parser
    - [Official Page](https://github.com/cdimascio/dotenv-kotlin)

- Openperm: Permission calculator
    - [Official Page](https://github.com/cufyorg/openperm-kt)

- Cufy Ktor Commons: Common utilities for `mangaka`,
  `kaguya` and `openperm-kt` with `ktor`
    - [Official Page](https://github.com/cufyorg/ktor-commons)

- Logback Classic: Core logging framework
    - [Official Page](https://logback.qos.ch/)
