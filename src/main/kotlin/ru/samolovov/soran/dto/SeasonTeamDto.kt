package ru.samolovov.soran.dto

class SeasonTeamDto(
    val id: Long? = null,
    val seasonId: Long,
    val teamId: Long,
    val playerIds: Set<Long>
)