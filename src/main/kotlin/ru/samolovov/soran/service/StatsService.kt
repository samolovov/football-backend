package ru.samolovov.soran.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.dto.PlayerStatsDto
import ru.samolovov.soran.dto.TeamStatsDto
import ru.samolovov.soran.repository.GameRepository

@Service
@Transactional
class StatsService(
    private val gameRepository: GameRepository
) {
    fun getForAllPlayers(): List<PlayerStatsDto> {
        return gameRepository.findAllPlayerStats()
    }

    fun getForAllPlayers(seasonId: Long): List<PlayerStatsDto> {
        return gameRepository.findAllPlayerStats(seasonId)
    }

    fun getForAllTeams(): List<TeamStatsDto> {
        return gameRepository.findAllTeamStats()
    }

    fun getForAllTeams(seasonId: Long): List<TeamStatsDto> {
        return gameRepository.findAllTeamStats(seasonId)
    }
}