package ru.samolovov.soran.dto

interface SeasonTeamGoalsDto {
    val teamId: Long
    val scored: Long
    val missed: Long
}