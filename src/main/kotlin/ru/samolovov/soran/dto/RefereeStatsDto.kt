package ru.samolovov.soran.dto

interface RefereeStatsDto {
    val id: Long
    val firstName: String
    val lastName: String
    val games: Long
    val yellows: Long
    val reds: Long
    val penalties: Long
}