package ru.samolovov.soran.repository

import org.springframework.data.repository.CrudRepository
import ru.samolovov.soran.entity.SeasonTeam

interface SeasonTeamRepository : CrudRepository<SeasonTeam, Long>