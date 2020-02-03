package ru.samolovov.soran.dto

import java.time.LocalDate

class TeamDto(
    val id: Long? = null,
    val name: String,
    val avatar: String? = null,
    val description: String? = null,
    val birthday: LocalDate? = null
)