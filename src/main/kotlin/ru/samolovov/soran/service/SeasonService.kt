package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.samolovov.soran.dto.GameResponseDto
import ru.samolovov.soran.dto.SeasonRequestDto
import ru.samolovov.soran.dto.SeasonResponseDto
import ru.samolovov.soran.entity.Season
import ru.samolovov.soran.entity.SeasonTeam
import ru.samolovov.soran.entity.Team
import ru.samolovov.soran.exception.SeasonNotFoundException
import ru.samolovov.soran.exception.TeamNotFoundException
import ru.samolovov.soran.exception.TeamsNotFoundException
import ru.samolovov.soran.exception.TournamentNotFoundException
import ru.samolovov.soran.repository.SeasonRepository
import ru.samolovov.soran.repository.TeamRepository
import ru.samolovov.soran.repository.TournamentRepository
import javax.transaction.Transactional

@Service
@Transactional
class SeasonService(
    private val tournamentRepository: TournamentRepository,
    private val teamRepository: TeamRepository,
    private val seasonRepository: SeasonRepository
) {
    private fun findTeamsOrThrow(teamIds: Set<Long>): Iterable<Team> {
        val teams = teamRepository.findAllById(teamIds)
        val foundTeamIds = teams.mapNotNull { it.id }
        val missedIds = teamIds - foundTeamIds
        if (missedIds.isNotEmpty()) throw TeamsNotFoundException(missedIds)
        return teams
    }

    fun create(seasonDto: SeasonRequestDto): SeasonResponseDto {
        val tournament = tournamentRepository.findByIdOrNull(seasonDto.tournamentId)
            ?: throw TournamentNotFoundException(seasonDto.tournamentId)

        val season = Season(
            tournament = tournament,
            startDate = seasonDto.startDate,
            endDate = seasonDto.endDate
        )
        season.teams = findTeamsOrThrow(seasonDto.teamIds).map { SeasonTeam(season, it) }.toSet()

        return seasonRepository.save(season).toSeasonDto()
    }

    fun update(id: Long, seasonDto: SeasonRequestDto): SeasonResponseDto {
        val season = seasonRepository.findByIdOrNull(id) ?: throw SeasonNotFoundException(id)
        season.update(seasonDto)
        return seasonRepository.save(season).toSeasonDto()
    }

    fun loadById(id: Long): SeasonResponseDto {
        return seasonRepository.findByIdOrNull(id)?.toSeasonDto() ?: throw TeamNotFoundException(id)
    }

    fun loadAll() = seasonRepository.findAll().map { it.toSeasonDto() }

    fun loadByTournamentId(id: Long) = seasonRepository.findByTournamentId(id)

    fun loadGames(id: Long): List<GameResponseDto> {
        val season = seasonRepository.findByIdOrNull(id) ?: throw SeasonNotFoundException(id)
        return season.games.map { it.toGameDto() }
    }

    private fun Season.update(seasonDto: SeasonRequestDto) {
        this.startDate = seasonDto.startDate
        this.endDate = seasonDto.endDate
        this.teams = findTeamsOrThrow(seasonDto.teamIds).map { SeasonTeam(this, it) }.toMutableSet()
    }
}

internal fun Season.toSeasonDto() = SeasonResponseDto(
    id = id,
    startDate = startDate,
    endDate = endDate,
    tournament = tournament.toTournamentDto(),
    teams = teams.map { it.team.toTeamDto() }
)