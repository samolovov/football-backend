package ru.samolovov.soran.dto

import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Past

class TeamDto(
    val id: Long? = null,
    @field:NotBlank
    val name: String,
    val avatar: String? = null,
    val description: String? = null,
    @field:Past
    val birthday: LocalDate? = null
)