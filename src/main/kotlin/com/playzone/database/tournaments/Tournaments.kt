package com.playzone.database.tournaments

import com.playzone.database.games.GameDTO
import com.playzone.database.games.Games
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object Tournaments: Table("tournaments") {

    private val tournamentId = Tournaments.varchar("tournament_id", 100)
    private val name = Tournaments.varchar("name", 100)
    private val status = Tournaments.integer("status")
    private val format = Tournaments.varchar("format", 20)
    private val prize = Tournaments.varchar("prize", 25)
    private val viewers = Tournaments.integer("viewers")

    fun insert(tournamentDTO: TournamentDTO) {
        transaction {
            Tournaments.insert {
                it[tournamentId] = tournamentDTO.tournamentId
                it[name] = tournamentDTO.name
                it[status] = tournamentDTO.status
                it[format] = tournamentDTO.format
                it[prize] = tournamentDTO.prize
                it[viewers] = tournamentDTO.viewers
            }
        }
    }
}