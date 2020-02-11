package ru.samolovov.soran.repository

import org.springframework.data.repository.CrudRepository
import ru.samolovov.soran.entity.Game

interface GameRepository: CrudRepository<Game, Long>