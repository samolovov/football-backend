package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.GameDto
import ru.samolovov.soran.dto.PlayerDto
import ru.samolovov.soran.dto.Position
import ru.samolovov.soran.dto.RefereeDto
import ru.samolovov.soran.dto.SeasonDto
import ru.samolovov.soran.dto.TeamDto
import ru.samolovov.soran.dto.TournamentDto

@Transactional
class GameServiceTest(
    @Autowired
    private val gameService: GameService,
    @Autowired
    private val seasonService: SeasonService,
    @Autowired
    private val teamService: TeamService,
    @Autowired
    private val playerService: PlayerService,
    @Autowired
    private val tournamentService: TournamentService,
    @Autowired
    private val refereeService: RefereeService,
    @Autowired
    private val seasonTeamService: SeasonTeamService
) : IntegrationTests() {
    lateinit var referee: RefereeDto
    lateinit var player: PlayerDto
    lateinit var team1: TeamDto
    lateinit var team2: TeamDto
    lateinit var tournament: TournamentDto
    lateinit var season: SeasonDto

    @BeforeEach
    internal fun setUp() {
        referee = refereeService.create(buildReferee("Pier", "Kollina"))
        player = playerService.create(buildPlayer("Diego", "Maradona", Position.FORWARD))
        team1 = teamService.create(buildTeam("Napoli"))
        team2 = teamService.create(buildTeam("Milan"))
        tournament = tournamentService.create(buildTournament("Serie A"))
        season = seasonService.create(buildSeason(tournament.id!!, setOf(team1.id!!, team2.id!!)))
        seasonTeamService.update(season.id!!, team1.id!!, buildSeasonTeam(season.id!!, team1.id!!, setOf(player.id!!)))
    }

    @Test
    fun `create game in season`() {
        val notCreated = buildNew()
        val created = gameService.create(notCreated)
        val loaded = gameService.loadById(created.id!!)

        assertThat(created.id!!).isNotNull()

        assertEquals(created, notCreated)
        assertEquals(loaded, notCreated)
    }

    @Test
    fun `update game in season`() {
        val team3 = teamService.create(buildTeam("Inter"))
        val referee2 = refereeService.create(buildReferee("Andreas", "Evrebe"))

        val created = gameService.create(buildNew())

        val notUpdated = buildGame(
            seasonId = created.id!!,
            firstTeamId = created.firstTeamId,
            secondTeamId = team3.id!!,
            firstTeamGoals = 4,
            secondTeamGoals = 1,
            refereeId = referee2.id!!
        )

        val updated = gameService.update(created.id!!, notUpdated)
        val loaded = gameService.loadById(created.id!!)

        assertEquals(updated, notUpdated)
        assertEquals(loaded, notUpdated)
    }

    private fun assertEquals(first: GameDto, second: GameDto) {
        assertThat(first.seasonId).isEqualTo(second.seasonId)
        assertThat(first.firstTeamId).isEqualTo(second.firstTeamId)
        assertThat(first.secondTeamId).isEqualTo(second.secondTeamId)
        assertThat(first.firstTeamGoals).isEqualTo(second.firstTeamGoals)
        assertThat(first.secondTeamGoals).isEqualTo(second.secondTeamGoals)
        assertThat(first.refereeId).isEqualTo(second.refereeId)
    }

    private fun buildNew() = buildGame(
        seasonId = season.id!!,
        firstTeamId = team1.id!!,
        secondTeamId = team2.id!!,
        firstTeamGoals = 3,
        secondTeamGoals = 0,
        refereeId = referee.id!!
    )
}