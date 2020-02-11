package ru.samolovov.soran.dto

import java.time.LocalDate

data class RefereeDto(
    val id: Long? = null,
    val firstName: String,
    val lastName: String,
    val avatar: String? = null,
    val birthday: LocalDate? = null
)