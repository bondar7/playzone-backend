package com.playzone.plugins

import com.playzone.cache.InMemoryCache
import com.playzone.cache.TokenCache
import com.playzone.login.LoginReceiveRemote
import com.playzone.login.LoginResponseRemote
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import java.util.UUID


fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
