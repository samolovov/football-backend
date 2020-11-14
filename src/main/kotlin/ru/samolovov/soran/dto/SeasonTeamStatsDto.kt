package ru.samolovov.soran.dto

interface SeasonTeamStatsDto {
    val teamId: Long
    val scored: Long
    val missed: Long
    val wins: Long
    val draws: Long
    val loses: Long
}