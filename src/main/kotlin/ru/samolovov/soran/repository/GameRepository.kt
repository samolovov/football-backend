package ru.samolovov.soran.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.samolovov.soran.dto.SeasonScorerDto
import ru.samolovov.soran.entity.Game

interface GameRepository : CrudRepository<Game, Long> {
    @Query(
        """select new ru.samolovov.soran.dto.SeasonScorerDto(p.id, p.firstName, p.lastName, count(gd.id)) from Game g
            join g.details gd 
            join gd.player p
            where gd.type='GOAL' and g.season.id = :seasonId group by p.id"""
    )
    fun findScorersBySeason(@Param("seasonId") seasonId: Long): List<SeasonScorerDto>
}