package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.GameDetailsDto
import ru.samolovov.soran.dto.GameDto
import ru.samolovov.soran.dto.PlayerDto
import ru.samolovov.soran.dto.Position
import ru.samolovov.soran.dto.RefereeDto
import ru.samolovov.soran.dto.SeasonDto
import ru.samolovov.soran.dto.TeamDto
import ru.samolovov.soran.dto.TournamentDto
import ru.samolovov.soran.dto.Type
import ru.samolovov.soran.exception.GameDetailsNotFoundException
import kotlin.random.Random

@Transactional
class GameDetailsServiceTest(
    @Autowired
    private val gameService: GameService,
    @Autowired
    private val gameDetailsService: GameDetailsService,
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
    lateinit var game: GameDto
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
        game = gameService.create(buildGame())
    }

    @Test
    fun `create game details`() {
        val notCreated = buildGameDetails()
        val created = gameDetailsService.create(game.id!!, notCreated)
        val loaded = gameDetailsService.loadById(created.id!!)

        assertThat(created.id!!).isNotNull()

        assertEquals(created, notCreated)
        assertEquals(loaded, notCreated)
    }

    @Test
    fun `update game details`() {
        val created = gameDetailsService.create(game.id!!, buildGameDetails())
        val notUpdated = GameDetailsDto(
            gameId = game.id!!,
            teamId = team2.id!!,
            playerId = player.id!!,
            type = Type.RED,
            minute = 50
        )

        val updated = gameDetailsService.update(created.id!!, notUpdated)
        val loaded = gameDetailsService.loadById(created.id!!)

        assertEquals(updated, notUpdated)
        assertEquals(loaded, notUpdated)
    }

    @Test
    fun `delete game details`() {
        val created = gameDetailsService.create(game.id!!, buildGameDetails())
        gameDetailsService.delete(created.id!!)

        assertThatExceptionOfType(GameDetailsNotFoundException::class.java).isThrownBy {
            gameDetailsService.loadById(created.id!!)
        }
    }

    private fun assertEquals(first: GameDetailsDto, second: GameDetailsDto) {
        assertThat(first.gameId).isEqualTo(second.gameId)
        assertThat(first.teamId).isEqualTo(second.teamId)
        assertThat(first.playerId).isEqualTo(second.playerId)
        assertThat(first.type).isEqualTo(second.type)
        assertThat(first.minute).isEqualTo(second.minute)
    }

    private fun buildGameDetails() = GameDetailsDto(
        gameId = game.id!!,
        teamId = team1.id!!,
        playerId = player.id!!,
        type = Type.NORMAL_GOAL,
        minute = Random.nextInt(0, 90)
    )

    private fun buildGame() = buildGame(
        seasonId = season.id!!,
        firstTeamId = team1.id!!,
        secondTeamId = team2.id!!,
        firstTeamGoals = 3,
        secondTeamGoals = 0,
        refereeId = referee.id!!
    )
}