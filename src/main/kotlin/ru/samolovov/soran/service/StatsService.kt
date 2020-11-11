package ru.samolovov.soran.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.dto.SeasonScorerDto
import ru.samolovov.soran.dto.SeasonTeamGoalsDto
import ru.samolovov.soran.repository.GameRepository

@Service
@Transactional
class StatsService(
    private val gameRepository: GameRepository
) {
    fun getScorers(seasonId: Long): List<SeasonScorerDto> {
        return gameRepository.findScorersBySeason(seasonId)
    }

    fun getTeamGoals(seasonId: Long): List<SeasonTeamGoalsDto> {
        return gameRepository.findTeamGoalsBySeason(seasonId)
    }
}