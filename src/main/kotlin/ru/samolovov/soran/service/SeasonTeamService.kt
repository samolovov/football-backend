package ru.samolovov.soran.service

import org.springframework.stereotype.Service
import ru.samolovov.soran.dto.SeasonTeamDto
import ru.samolovov.soran.entity.Player
import ru.samolovov.soran.entity.SeasonTeam
import ru.samolovov.soran.exception.PlayersNotFoundException
import ru.samolovov.soran.exception.SeasonTeamNotFoundException
import ru.samolovov.soran.repository.PlayerRepository
import ru.samolovov.soran.repository.SeasonTeamRepository
import javax.transaction.Transactional

@Service
@Transactional
class SeasonTeamService(
    private val seasonTeamRepository: SeasonTeamRepository,
    private val playerRepository: PlayerRepository
) {
    fun update(seasonId: Long, teamId: Long, seasonTeamDto: SeasonTeamDto): SeasonTeamDto {
        val seasonTeam = seasonTeamRepository.findBySeasonIdAndTeamId(seasonId, teamId)
            ?: throw SeasonTeamNotFoundException(seasonId, teamId)

        seasonTeam.players = findPlayersOrThrow(seasonTeamDto.playerIds).toSet()

        return seasonTeamRepository.save(seasonTeam).toDto()
    }

    private fun findPlayersOrThrow(playerIds: Set<Long>): Iterable<Player> {
        val players = playerRepository.findAllById(playerIds)
        val missedIds = playerIds - players.mapNotNull { it.id }
        if (missedIds.isNotEmpty()) throw PlayersNotFoundException(missedIds)
        return players
    }

    private fun SeasonTeam.toDto() = SeasonTeamDto(
        id = id,
        seasonId = season.id!!,
        teamId = team.id!!,
        playerIds = players.map { it.id!! }.toSet()
    )
}