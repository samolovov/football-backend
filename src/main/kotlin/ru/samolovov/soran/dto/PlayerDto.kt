package ru.samolovov.soran.dto

import java.time.LocalDate

data class PlayerDto(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val avatar: String? = null,
    val position: Position? = null,
    val birthday: LocalDate? = null
)

enum class Position {
    GOALKEEPER, DEFENDER, MIDFIELDER, FORWARD
}