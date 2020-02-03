package ru.samolovov.soran.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.samolovov.soran.entity.Player

@Repository
interface PlayerRepository : CrudRepository<Player, Long> {
}