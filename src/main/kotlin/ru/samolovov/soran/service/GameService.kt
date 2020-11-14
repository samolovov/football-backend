package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.samolovov.soran.dto.GameDto
import ru.samolovov.soran.entity.Game
import ru.samolovov.soran.exception.GameNotFoundException
import ru.samolovov.soran.exception.RefereeNotFoundException
import ru.samolovov.soran.exception.SeasonNotFoundException
import ru.samolovov.soran.exception.TeamNotFoundException
import ru.samolovov.soran.repository.GameRepository
import ru.samolovov.soran.repository.RefereeRepository
import ru.samolovov.soran.repository.SeasonRepository
import ru.samolovov.soran.repository.TeamRepository
import javax.transaction.Transactional

@Service
@Transactional
class GameService(
    private val gameRepository: GameRepository,
    private val seasonRepository: SeasonRepository,
    private val teamRepository: TeamRepository,
    private val refereeRepository: RefereeRepository
) {
    fun create(gameDto: GameDto): GameDto {
        val season =
            seasonRepository.findByIdOrNull(gameDto.seasonId) ?: throw SeasonNotFoundException(gameDto.seasonId)
        val firstTeam =
            teamRepository.findByIdOrNull(gameDto.firstTeamId) ?: throw TeamNotFoundException(gameDto.firstTeamId)
        val secondTeam =
            teamRepository.findByIdOrNull(gameDto.secondTeamId) ?: throw TeamNotFoundException(gameDto.secondTeamId)
        val referee =
            refereeRepository.findByIdOrNull(gameDto.refereeId) ?: throw RefereeNotFoundException(gameDto.refereeId)

        return gameRepository.save(
            with(gameDto) {
                Game(
                    season = season,
                    firstTeam = firstTeam,
                    secondTeam = secondTeam,
                    firstTeamGoals = firstTeamGoals,
                    secondTeamGoals = secondTeamGoals,
                    referee = referee,
                    date = date
                )
            }).toGameDto()
    }

    fun update(id: Long, gameDto: GameDto): GameDto {
        val game = gameRepository.findByIdOrNull(id) ?: throw GameNotFoundException(id)
        //don't update season
        val firstTeam =
            teamRepository.findByIdOrNull(gameDto.firstTeamId) ?: throw TeamNotFoundException(gameDto.firstTeamId)
        val secondTeam =
            teamRepository.findByIdOrNull(gameDto.secondTeamId) ?: throw TeamNotFoundException(gameDto.secondTeamId)
        val referee =
            refereeRepository.findByIdOrNull(gameDto.refereeId) ?: throw RefereeNotFoundException(gameDto.refereeId)

        game.firstTeam = firstTeam
        game.secondTeam = secondTeam
        game.firstTeamGoals = gameDto.firstTeamGoals
        game.secondTeamGoals = gameDto.secondTeamGoals
        game.referee = referee
        game.date = gameDto.date

        return gameRepository.save(game).toGameDto()
    }

    fun loadById(id: Long) =
        gameRepository.findByIdOrNull(id)?.toGameDto() ?: throw GameNotFoundException(id)

    fun loadAll() = gameRepository.findAll().map { it.toGameDto() }
}

internal fun Game.toGameDto() = GameDto(
    id = id,
    seasonId = season.id!!,
    firstTeamId = firstTeam.id!!,
    secondTeamId = secondTeam.id!!,
    firstTeamGoals = firstTeamGoals,
    secondTeamGoals = secondTeamGoals,
    refereeId = referee.id!!,
    date = date
)