package ru.samolovov.soran.dto

import java.time.LocalDate

class GameDto(
    val id: Long? = null,
    val seasonId: Long,
    val firstTeamId: Long,
    val secondTeamId: Long,
    val firstTeamGoals: Int,
    val secondTeamGoals: Int,
    val date: LocalDate,
    val refereeId: Long
)