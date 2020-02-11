package ru.samolovov.soran.dto

class GameRequestDto(
    val id: Long?,
    val seasonId: Long,
    val firstTeamId: Long,
    val secondTeamId: Long,
    val firstTeamGoals: Int,
    val secondTeamGoals: Int,
    val refereeId: Long
)