package ru.samolovov.soran.dto

class GameResponseDto(
    val id: Long?,
    val season: SeasonDto,
    val firstTeam: TeamDto,
    val secondTeam: TeamDto,
    val firstTeamGoals: Int,
    val secondTeamGoals: Int,
    val referee: RefereeDto
)