package ru.samolovov.soran.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.samolovov.soran.entity.Season

@Repository
interface SeasonRepository : CrudRepository<Season, Long> {
    fun findByTournamentId(id: Long): List<Season>
}