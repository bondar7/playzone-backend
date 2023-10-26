package com.playzone.database.users

import org.jetbrains.exposed.sql.Except
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object Users: Table("users") {
    private val login = Users.varchar("login",25)
    private val email = Users.varchar("email", 25)
    private val password = Users.varchar("password", 25)
    private val username = Users.varchar("username", 30)

    fun insert(userDTO: UserDTO) {
        // все повинні робити в transaction scope
        transaction {
            Users.insert {
                it[login] = userDTO.login
                it[email] = userDTO.email ?: ""
                it[password] = userDTO.password
                it[username] = userDTO.username
            }
        }
    }

    fun fetchUser(login: String): UserDTO? {
        return try {
            transaction {
                val user = Users.select { Users.login.eq(login) }.single()
                UserDTO(
                    login = user[Users.login],
                    email = user[email],
                    password = user[password],
                    username = user[username]
                )
            }
        } catch (e: Exception) {
            null
        }
    }
}