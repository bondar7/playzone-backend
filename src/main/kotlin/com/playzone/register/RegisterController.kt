package com.playzone.register

import com.playzone.database.tokens.TokenDTO
import com.playzone.database.tokens.Tokens
import com.playzone.database.users.UserDTO
import com.playzone.database.users.Users
import com.playzone.utils.isValidEmail
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(
    val call: ApplicationCall
) {

    suspend fun registerNewUser() {

        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.email.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Email is not valid")
        }

        val userDTO = Users.fetchUser(registerReceiveRemote.login)

        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User is already signed up")
        } else {
            val token = UUID.randomUUID().toString()
            val rowId = UUID.randomUUID().toString()

            try {
                Users.insert(
                    UserDTO(
                        login = registerReceiveRemote.login,
                        email = registerReceiveRemote.email,
                        password = registerReceiveRemote.password,
                        username = ""
                    )
                )
            } catch (e: ExposedSQLException) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }
            Tokens.insert(
                TokenDTO(
                    rowId = rowId,
                    login = registerReceiveRemote.login,
                    token = token
                )
            )
            call.respond(RegisterResponseRemote(token = token))
        }

    }
}