package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.samolovov.soran.dto.SeasonRequestDto
import ru.samolovov.soran.dto.SeasonResponseDto
import ru.samolovov.soran.entity.Season
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
    private fun checkMissedTeams(givenTeamIds: Set<Long>, teams: Iterable<Team>) {
        val foundTeamIds = teams.mapNotNull { it.id }
        val missedIds = givenTeamIds - foundTeamIds
        if (missedIds.isNotEmpty()) throw TeamsNotFoundException(missedIds)
    }

    fun create(season: SeasonRequestDto): SeasonResponseDto {
        val tournament = tournamentRepository.findByIdOrNull(season.tournamentId) ?: throw TournamentNotFoundException(
            season.tournamentId
        )
        val teams = teamRepository.findAllById(season.teamIds)
        checkMissedTeams(season.teamIds, teams)

        return seasonRepository.save(
            with(season) {
                Season(
                    tournament = tournament,
                    teams = teams.toSet(),
                    startDate = startDate,
                    endDate = endDate
                )
            }
        ).toSeasonDto()
    }

    fun update(id: Long, seasonDto: SeasonRequestDto): SeasonResponseDto {
        val season = seasonRepository.findByIdOrNull(id) ?: throw SeasonNotFoundException(id)
        //we don't need to update tournament, only teams and dates
        val teams = teamRepository.findAllById(seasonDto.teamIds)
        checkMissedTeams(seasonDto.teamIds, teams)

        season.startDate = seasonDto.startDate
        season.endDate = seasonDto.endDate
        season.teams = teams.toSet()

        return seasonRepository.save(season).toSeasonDto()
    }

    fun loadById(id: Long): SeasonResponseDto {
        return seasonRepository.findByIdOrNull(id)?.toSeasonDto() ?: throw TeamNotFoundException(id)
    }

    fun loadAll() = seasonRepository.findAll().map { it.toSeasonDto() }

    fun loadByTournamentId(id: Long) = seasonRepository.findByTournamentId(id)
}

private fun Season.toSeasonDto() = SeasonResponseDto(
    id = id,
    startDate = startDate,
    endDate = endDate,
    tournament = tournament.toTournamentDto(),
    teams = teams.map { it.toTeamDto() }
)