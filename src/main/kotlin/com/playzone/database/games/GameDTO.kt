package com.playzone.database.games

import com.playzone.games.models.CreateGameRequest
import com.playzone.games.models.CreateGameResponse
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class GameDTO(
    val gameID: String,
    val title: String,
    val description: String,
    val version: String,
    val size: Double
)

fun CreateGameRequest.mapToGameDTO(): GameDTO =
    GameDTO(
        gameID = UUID.randomUUID().toString(),
        title = title,
        description = description,
        version = version,
        size = size
    )

fun GameDTO.mapToCreateGameResponse(): CreateGameResponse =
    CreateGameResponse(
        gameID = gameID,
        title = title,
        description = description,
        version = version,
        size = size
    )