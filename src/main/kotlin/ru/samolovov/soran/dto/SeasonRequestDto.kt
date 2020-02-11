package ru.samolovov.soran.dto

import java.time.LocalDate

class SeasonRequestDto(
    val id: Long? = null,
    val tournamentId: Long,
    val teamIds: Set<Long> = emptySet(),
    val startDate: LocalDate,
    val endDate: LocalDate
)