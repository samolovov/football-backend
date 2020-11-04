package ru.samolovov.soran.repository

import org.springframework.data.repository.CrudRepository
import ru.samolovov.soran.entity.GameDetails

interface GameDetailsRepository : CrudRepository<GameDetails, Long>