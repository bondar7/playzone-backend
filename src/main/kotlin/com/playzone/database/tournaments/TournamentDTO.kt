package com.playzone.database.tournaments

class TournamentDTO (
    val tournamentId: String,
    val name: String,
    val status: Int,
    val format: String,
    val prize: String,
    val viewers: Int,
)