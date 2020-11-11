package ru.samolovov.soran.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.samolovov.soran.dto.SeasonScorerDto
import ru.samolovov.soran.dto.SeasonTeamGoalsDto
import ru.samolovov.soran.entity.Game

interface GameRepository : CrudRepository<Game, Long> {
    @Query(
        """select p.id as id, p.firstName as firstName, p.lastName as lastName, count(gd.id) as goals from Game g
            join g.details gd 
            join gd.player p
            where gd.type='GOAL' and g.season.id = :seasonId group by p.id"""
    )
    fun findScorersBySeason(@Param("seasonId") seasonId: Long): List<SeasonScorerDto>

    @Query(
        nativeQuery = true, value = """
        select tmp.teamId as teamId, sum(tmp.scored) as scored, sum(tmp.missed) as missed from (
            select g.first_team_id as teamId, sum(g.first_team_goals) as scored, sum(g.second_team_goals) as missed 
            from games g where g.season_id = :seasonId group by g.first_team_id
              union 
            select g.second_team_id as teamId, sum(g.second_team_goals) as scored, sum(g.first_team_goals) as missed 
            from games g where g.season_id = :seasonId group by g.second_team_id
            ) as tmp 
        group by tmp.teamId
    """
    )
    fun findTeamGoalsBySeason(@Param("seasonId") seasonId: Long): List<SeasonTeamGoalsDto>
}