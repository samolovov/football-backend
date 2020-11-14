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
    NORMAL_GOAL, PENALTY_GOAL, AUTO_GOAL, PENALTY_MISS, YELLOW, RED, PENALTY
}