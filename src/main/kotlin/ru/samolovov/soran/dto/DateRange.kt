package ru.samolovov.soran.dto

import java.time.LocalDate

interface DateRange {
    val startDate: LocalDate
    val endDate: LocalDate
}