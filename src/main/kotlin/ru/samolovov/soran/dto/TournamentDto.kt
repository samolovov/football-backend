package ru.samolovov.soran.dto

import javax.validation.constraints.NotBlank

class TournamentDto(
    val id: Long? = null,
    @field:NotBlank
    val name: String
)