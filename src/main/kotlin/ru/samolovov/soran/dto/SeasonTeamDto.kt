package ru.samolovov.soran.dto

class SeasonTeamDto(
    val seasonId: Long,
    val teamId: Long,
    val playerIds: Set<Long>
)