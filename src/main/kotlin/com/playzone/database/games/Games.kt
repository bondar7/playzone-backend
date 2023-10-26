package com.playzone.database.games

import com.playzone.database.tokens.TokenDTO
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object Games : Table("games") {

    private val gameId = Games.varchar(name = "gameId", length = 100)
    private val name = Games.varchar(name = "name", length = 150)
    private val description = Games.varchar(name = "description", length = 500)
    private var version = Games.varchar(name = "version", length = 20)
    private var weight = Games.double(name = "weight")

    fun insert(gameDTO: GameDTO) {
        transaction {
            Games.insert {
                it[gameId] = gameDTO.gameID
                it[name] = gameDTO.title
                it[description] = gameDTO.description
                it[version] = gameDTO.version
                it[weight] = gameDTO.size
            }
        }
    }

    fun fetchAll(): List<GameDTO> {
        return try {
            transaction {
                Games.selectAll().toList()
                    .map {
                        GameDTO(
                            gameID = it[gameId],
                            title = it[name],
                            description = it[description],
                            version = it[version],
                            size = it[weight]
                        )
                    }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}