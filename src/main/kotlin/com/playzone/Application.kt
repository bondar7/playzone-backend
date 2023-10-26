package com.playzone

import com.playzone.games.configureGameRouting
import com.playzone.login.configureLoginRouting
import com.playzone.plugins.*
import com.playzone.register.configureRegisterRouting
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect("jdbc:postgresql://localhost:5432/playzone", driver = "org.postgresql.Driver",
        user = "postgres", password = "23105")

    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureRouting()
    configureLoginRouting()
    configureRegisterRouting()
    configureGameRouting()
    configureSerialization()
}
