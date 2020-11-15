package ru.samolovov.soran.dto

import ru.samolovov.soran.validation.ConsistentDates
import java.time.LocalDate

@ConsistentDates
class SeasonDto(
    val id: Long? = null,
    val tournamentId: Long,
    val teamIds: Set<Long> = emptySet(),
    override val startDate: LocalDate,
    override val endDate: LocalDate
) : DateRange