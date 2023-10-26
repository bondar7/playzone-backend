package com.playzone.login

import com.playzone.database.tokens.TokenDTO
import com.playzone.database.tokens.Tokens
import com.playzone.database.users.Users
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.util.UUID

class LoginController(
    val call: ApplicationCall
) {

    suspend fun loginUser() {
        val loginReceiveRemote = call.receive<LoginReceiveRemote>()
        val userDTO = Users.fetchUser(loginReceiveRemote.login)

        if (userDTO == null) {
            call.respond(HttpStatusCode.BadRequest, "User is not signed up")
        } else {
            if (userDTO.password == loginReceiveRemote.password) {
                val token = UUID.randomUUID().toString()
                val rowId = UUID.randomUUID().toString()
                Tokens.insert(
                    TokenDTO(
                        rowId = rowId,
                        login = userDTO.login,
                        token = token
                    )
                )
                return call.respond(LoginResponseRemote(token))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Wrong password")
            }
        }
    }
}