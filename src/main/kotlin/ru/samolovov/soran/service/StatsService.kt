package ru.samolovov.soran.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.dto.SeasonPlayerStatsDto
import ru.samolovov.soran.dto.SeasonTeamStatsDto
import ru.samolovov.soran.repository.GameRepository

@Service
@Transactional
class StatsService(
    private val gameRepository: GameRepository
) {
    fun getForAllPlayers(seasonId: Long): List<SeasonPlayerStatsDto> {
        return gameRepository.findScorersBySeason(seasonId)
    }

    fun getForAllTeams(seasonId: Long): List<SeasonTeamStatsDto> {
        return gameRepository.findAllTeamStats(seasonId)
    }
}