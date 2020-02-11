package ru.samolovov.soran.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.samolovov.soran.entity.Referee

@Repository
interface RefereeRepository : CrudRepository<Referee, Long>