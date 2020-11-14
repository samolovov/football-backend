package ru.samolovov.soran.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.samolovov.soran.dto.PlayerStatsDto
import ru.samolovov.soran.dto.TeamStatsDto
import ru.samolovov.soran.entity.Game

interface GameRepository : CrudRepository<Game, Long> {
    @Query(PLAYERS_STATS_GLOBAL_JPQL)
    fun findAllPlayerStats(): List<PlayerStatsDto>

    @Query(PLAYERS_STATS_SEASON_JPQL)
    fun findAllPlayerStats(@Param("seasonId") seasonId: Long): List<PlayerStatsDto>

    @Query(
        nativeQuery = true, value = """
        select tmp.teamId as teamId, sum(tmp.scored) as scored, sum(tmp.missed) as missed,
            sum(tmp.wins) as wins, sum(tmp.draws) as draws, sum(tmp.loses) as loses from (
                select g.first_team_id as teamId, sum(g.first_team_goals) as scored, sum(g.second_team_goals) as missed,
                    sum(case when (g.first_team_goals > g.second_team_goals) then 1 else 0 end) as wins,
                    sum(case when (g.first_team_goals = g.second_team_goals) then 1 else 0 end) as draws,
                    sum(case when (g.first_team_goals < g.second_team_goals) then 1 else 0 end) as loses
                from games g where g.season_id = :seasonId group by g.first_team_id
                  union 
                select g.second_team_id as teamId, sum(g.second_team_goals) as scored, sum(g.first_team_goals) as missed,
                    sum(case when (g.first_team_goals < g.second_team_goals) then 1 else 0 end) as wins,
                    sum(case when (g.first_team_goals = g.second_team_goals) then 1 else 0 end) as draws,
                    sum(case when (g.first_team_goals > g.second_team_goals) then 1 else 0 end) as loses
                from games g where g.season_id = :seasonId group by g.second_team_id
            ) as tmp 
        group by tmp.teamId
    """
    )
    fun findAllTeamStats(@Param("seasonId") seasonId: Long): List<TeamStatsDto>

    companion object {
        private const val PLAYERS_STATS_BEGIN = """
            select 
                p.id as id, 
                p.firstName as firstName, 
                p.lastName as lastName, 
                sum(case when gd.type = 'NORMAL_GOAL' then 1 else 0 end) as normalGoals,
                sum(case when gd.type = 'PENALTY_GOAL' then 1 else 0 end) as penaltyGoals,
                sum(case when gd.type = 'AUTO_GOAL' then 1 else 0 end) as autoGoals,
                sum(case when gd.type = 'YELLOW' then 1 else 0 end) as yellows,
                sum(case when gd.type = 'RED' then 1 else 0 end) as reds,
                sum(case when gd.type = 'PENALTY' then 1 else 0 end) as penalties,
                sum(case when gd.type = 'PENALTY_MISS' then 1 else 0 end) as penaltyMisses
            from Game g
                join g.details gd 
                join gd.player p
        """
        private const val PLAYERS_STATS_END = """ group by p.id """
        private const val PLAYERS_STATS_SEASON_CONDITION = """ where seasonId = :seasonId """
        private const val PLAYERS_STATS_SEASON_JPQL = PLAYERS_STATS_BEGIN + PLAYERS_STATS_SEASON_CONDITION + PLAYERS_STATS_END
        private const val PLAYERS_STATS_GLOBAL_JPQL = PLAYERS_STATS_BEGIN + PLAYERS_STATS_END
    }
}