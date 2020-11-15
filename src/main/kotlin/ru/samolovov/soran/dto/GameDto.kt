package ru.samolovov.soran.dto

import java.time.LocalDate
import javax.validation.constraints.PastOrPresent
import javax.validation.constraints.PositiveOrZero

class GameDto(
    val id: Long? = null,
    val seasonId: Long,
    val firstTeamId: Long,
    val secondTeamId: Long,
    @field:PositiveOrZero
    val firstTeamGoals: Int,
    @field:PositiveOrZero
    val secondTeamGoals: Int,
    @field:PastOrPresent
    val date: LocalDate,
    val refereeId: Long
)