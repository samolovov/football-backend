package ru.samolovov.soran.dto

import java.time.LocalDate

class SeasonResponseDto(
    val id: Long? = null,
    val tournament: TournamentDto,
    val teams: List<TeamDto> = emptyList(),
    val startDate: LocalDate,
    val endDate: LocalDate
)