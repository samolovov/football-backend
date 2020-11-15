package ru.samolovov.soran.dto

import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

data class PlayerDto(
    val id: Long? = null,
    @field:NotBlank
    val firstName: String,
    @field:NotBlank
    val lastName: String,
    val avatar: String? = null,
    val position: Position? = null,
    @field:Past
    val birthday: LocalDate? = null
)

enum class Position {
    GOALKEEPER, DEFENDER, MIDFIELDER, FORWARD
}