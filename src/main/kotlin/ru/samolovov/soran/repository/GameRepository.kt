package ru.samolovov.soran.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import ru.samolovov.soran.dto.PlayerStatsDto
import ru.samolovov.soran.dto.RefereeStatsDto
import ru.samolovov.soran.dto.TeamStatsDto
import ru.samolovov.soran.entity.Game

interface GameRepository : CrudRepository<Game, Long> {
    @Query(REFEREE_STATS_GLOBAL_JPQL)
    fun findRefereeStats(@Param("refereeId") refereeId: Long): RefereeStatsDto?

    @Query(REFEREES_STATS_GLOBAL_JPQL)
    fun findAllRefereeStats(): List<RefereeStatsDto>

    @Query(PLAYER_STATS_GLOBAL_JPQL)
    fun findPlayerStats(@Param("playerId") playerId: Long): PlayerStatsDto?

    @Query(PLAYERS_STATS_GLOBAL_JPQL)
    fun findAllPlayerStats(): List<PlayerStatsDto>

    @Query(PLAYERS_STATS_SEASON_JPQL)
    fun findAllPlayerStats(@Param("seasonId") seasonId: Long): List<PlayerStatsDto>

    @Query(nativeQuery = true, value = TEAM_STATS_GLOBAL_SQL)
    fun findTeamStats(@Param("teamId") teamId: Long): TeamStatsDto?

    @Query(nativeQuery = true, value = TEAMS_STATS_GLOBAL_SQL)
    fun findAllTeamStats(): List<TeamStatsDto>

    @Query(nativeQuery = true, value = TEAMS_STATS_SEASON_SQL)
    fun findAllTeamStats(@Param("seasonId") seasonId: Long): List<TeamStatsDto>

    companion object {
        //players
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
        private const val PLAYERS_STATS_SEASON_JPQL = PLAYERS_STATS_BEGIN +
                """ where g.season.id = :seasonId """ +
                PLAYERS_STATS_END
        private const val PLAYERS_STATS_GLOBAL_JPQL = PLAYERS_STATS_BEGIN + PLAYERS_STATS_END
        private const val PLAYER_STATS_GLOBAL_JPQL = PLAYERS_STATS_BEGIN +
                """ where p.id = :playerId """ +
                PLAYERS_STATS_END

        //teams
        private const val TEAMS_STATS_BEGIN = """
            select 
                tmp.teamId as teamId, 
                sum(tmp.games) as games, 
                sum(tmp.scored) as scored, 
                sum(tmp.missed) as missed,
                sum(tmp.wins) as wins,
                sum(tmp.draws) as draws,
                sum(tmp.loses) as loses from (
                    select 
                        g.first_team_id as teamId, 
                        count(g.id) as games,
                        sum(g.first_team_goals) as scored, 
                        sum(g.second_team_goals) as missed,
                        sum(case when (g.first_team_goals > g.second_team_goals) then 1 else 0 end) as wins,
                        sum(case when (g.first_team_goals = g.second_team_goals) then 1 else 0 end) as draws,
                        sum(case when (g.first_team_goals < g.second_team_goals) then 1 else 0 end) as loses
                    from games g """
        private const val TEAMS_STATS_MIDDLE = """ group by g.first_team_id
                        union 
                    select 
                        g.second_team_id as teamId,
                        count(g.id) as games,
                        sum(g.second_team_goals) as scored, 
                        sum(g.first_team_goals) as missed,
                        sum(case when (g.first_team_goals < g.second_team_goals) then 1 else 0 end) as wins,
                        sum(case when (g.first_team_goals = g.second_team_goals) then 1 else 0 end) as draws,
                        sum(case when (g.first_team_goals > g.second_team_goals) then 1 else 0 end) as loses
                    from games g """
        private const val TEAMS_STATS_END = """group by g.second_team_id
                ) as tmp 
            group by tmp.teamId
        """
        private const val TEAMS_STATS_SEASON_CONDITION = """ where g.season_id = :seasonId """
        private const val TEAMS_STATS_ID_CONDITION = """ where g.team_id = :teamId """
        private const val TEAMS_STATS_SEASON_SQL = TEAMS_STATS_BEGIN +
                TEAMS_STATS_SEASON_CONDITION +
                TEAMS_STATS_MIDDLE +
                TEAMS_STATS_SEASON_CONDITION +
                TEAMS_STATS_END

        private const val TEAM_STATS_GLOBAL_SQL = TEAMS_STATS_BEGIN +
                TEAMS_STATS_ID_CONDITION +
                TEAMS_STATS_MIDDLE +
                TEAMS_STATS_ID_CONDITION +
                TEAMS_STATS_END

        private const val TEAMS_STATS_GLOBAL_SQL = TEAMS_STATS_BEGIN +
                TEAMS_STATS_MIDDLE +
                TEAMS_STATS_END

        //referees
        private const val REFEREES_STATS_BEGIN = """
            select 
                r.id as id, 
                r.firstName as firstName, 
                r.lastName as lastName, 
                count(distinct g.id) as games,
                sum(case when gd.type = 'YELLOW' then 1 else 0 end) as yellows,
                sum(case when gd.type = 'RED' then 1 else 0 end) as reds,
                sum(case when gd.type = 'PENALTY' then 1 else 0 end) as penalties
            from Game g
                join g.referee r
                join g.details gd
        """
        private const val REFEREES_STATS_END = """ group by r.id """
        private const val REFEREE_STATS_GLOBAL_JPQL = REFEREES_STATS_BEGIN +
                """ where r.id = :refereeId """ +
                REFEREES_STATS_END
        private const val REFEREES_STATS_GLOBAL_JPQL = REFEREES_STATS_BEGIN + REFEREES_STATS_END
    }
}