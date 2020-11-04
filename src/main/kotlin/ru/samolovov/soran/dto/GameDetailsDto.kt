package ru.samolovov.soran.dto

class GameDetailsDto(
    val id: Long? = null,
    val gameId: Long,
    val teamId: Long,
    val playerId: Long,
    val type: Type,
    val minute: Int
)

enum class Type {
    GOAL, AUTOGOAL, YELLOW, RED
}