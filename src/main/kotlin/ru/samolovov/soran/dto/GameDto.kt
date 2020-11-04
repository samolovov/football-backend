package ru.samolovov.soran.dto

class GameDto(
    val id: Long? = null,
    val seasonId: Long,
    val firstTeamId: Long,
    val secondTeamId: Long,
    val firstTeamGoals: Int,
    val secondTeamGoals: Int,
    val refereeId: Long
)