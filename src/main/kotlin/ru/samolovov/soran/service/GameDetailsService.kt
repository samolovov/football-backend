package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.dto.GameDetailsDto
import ru.samolovov.soran.entity.GameDetails
import ru.samolovov.soran.exception.GameDetailsNotFoundException
import ru.samolovov.soran.exception.GameNotFoundException
import ru.samolovov.soran.exception.PlayerNotFoundException
import ru.samolovov.soran.exception.TeamNotFoundException
import ru.samolovov.soran.repository.GameDetailsRepository
import ru.samolovov.soran.repository.GameRepository
import ru.samolovov.soran.repository.PlayerRepository
import ru.samolovov.soran.repository.TeamRepository

@Service
@Transactional
class GameDetailsService(
    private val gameRepository: GameRepository,
    private val teamRepository: TeamRepository,
    private val playerRepository: PlayerRepository,
    private val gameDetailsRepository: GameDetailsRepository
) {

    fun create(gameId: Long, dto: GameDetailsDto): GameDetailsDto {
        val game = gameRepository.findByIdOrNull(gameId) ?: throw GameNotFoundException(gameId)
        val team = teamRepository.findByIdOrNull(dto.teamId) ?: throw TeamNotFoundException(dto.teamId)
        val player = playerRepository.findByIdOrNull(dto.playerId) ?: throw PlayerNotFoundException(dto.playerId)

        val gameDetails = GameDetails(
            game = game,
            team = team,
            player = player,
            type = dto.type,
            minute = dto.minute
        )

        return gameDetailsRepository.save(gameDetails).toDto()
    }

    fun update(id: Long, dto: GameDetailsDto): GameDetailsDto {
        val gameDetails = gameDetailsRepository.findByIdOrNull(id) ?: throw GameDetailsNotFoundException(id)
        val team = teamRepository.findByIdOrNull(dto.teamId) ?: throw TeamNotFoundException(dto.teamId)
        val player = playerRepository.findByIdOrNull(dto.playerId) ?: throw PlayerNotFoundException(dto.playerId)

        with(dto) {
            gameDetails.team = team
            gameDetails.player = player
            gameDetails.minute = minute
            gameDetails.type = type
        }

        return gameDetailsRepository.save(gameDetails).toDto()
    }

    fun loadById(id: Long) = gameDetailsRepository.findByIdOrNull(id)?.toDto() ?: throw GameDetailsNotFoundException(id)

    fun delete(id: Long) {
        gameDetailsRepository.deleteById(id)
    }

    private fun GameDetails.toDto() = GameDetailsDto(
        id = id,
        gameId = game.id!!,
        teamId = team.id!!,
        playerId = player.id!!,
        type = type,
        minute = minute
    )
}