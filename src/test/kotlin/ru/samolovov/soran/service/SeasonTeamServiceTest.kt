package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.Position
import ru.samolovov.soran.dto.SeasonDto
import ru.samolovov.soran.dto.SeasonTeamDto
import ru.samolovov.soran.dto.TeamDto
import ru.samolovov.soran.dto.TournamentDto

@Transactional
class SeasonTeamServiceTest(
    @Autowired
    private val seasonService: SeasonService,
    @Autowired
    private val seasonTeamService: SeasonTeamService,
    @Autowired
    private val tournamentService: TournamentService,
    @Autowired
    private val teamService: TeamService,
    @Autowired
    private val playerService: PlayerService
) : IntegrationTests() {

    lateinit var team: TeamDto
    lateinit var tournament: TournamentDto
    lateinit var season: SeasonDto

    @BeforeEach
    internal fun setUp() {
        tournament = tournamentService.create(buildTournament("Осеннее первенство"))
        team = teamService.create(buildTeam("Академ 2.0"))
        season = seasonService.create(buildSeason())
    }

    @Test
    @Rollback(false)
    fun `update season team with players`() {
        val player1 = playerService.create(buildPlayer("Vladimir", "Putin", Position.DEFENDER))
        val player2 = playerService.create(buildPlayer("Barak", "Obama", Position.GOALKEEPER))

        val seasonId = season.id!!
        val teamId = team.id!!

        val notUpdated = SeasonTeamDto(
            seasonId = seasonId, teamId = teamId, playerIds = setOf(player1.id!!, player2.id!!)
        )
        val updated = seasonTeamService.update(seasonId, teamId, notUpdated)

        assertThat(updated.id).isNotNull()
        assertEquals(updated, notUpdated)
    }

    private fun assertEquals(first: SeasonTeamDto, second: SeasonTeamDto) {
        assertThat(first.playerIds).isEqualTo(second.playerIds)
        assertThat(first.teamId).isEqualTo(second.teamId)
        assertThat(first.seasonId).isEqualTo(second.seasonId)
    }

    private fun buildSeason() = buildSeason(tournament.id!!, setOf(team.id!!))
}