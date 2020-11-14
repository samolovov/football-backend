package ru.samolovov.soran.dto

interface SeasonPlayerStatsDto {
    val id: Long
    val firstName: String
    val lastName: String
    val normalGoals: Long
    val penaltyGoals: Long
    val autoGoals: Long
    val yellows: Long
    val reds: Long
    val penalties: Long
    val penaltyMisses: Long
}