package ru.samolovov.soran.repository

import org.springframework.data.repository.CrudRepository
import ru.samolovov.soran.entity.Tournament

interface TournamentRepository : CrudRepository<Tournament, Long>