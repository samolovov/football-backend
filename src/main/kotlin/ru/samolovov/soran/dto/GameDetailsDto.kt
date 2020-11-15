package ru.samolovov.soran.dto

import javax.validation.constraints.PositiveOrZero

class GameDetailsDto(
    val id: Long? = null,
    val gameId: Long,
    val teamId: Long,
    val playerId: Long,
    val type: Type,
    @field:PositiveOrZero
    val minute: Int
)

enum class Type {
    NORMAL_GOAL, PENALTY_GOAL, AUTO_GOAL, PENALTY_MISS, YELLOW, RED, PENALTY
}