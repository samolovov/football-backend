package ru.samolovov.soran.dto

interface TeamStatsDto {
    val teamId: Long
    val games: Long
    val scored: Long
    val missed: Long
    val wins: Long
    val draws: Long
    val loses: Long
}