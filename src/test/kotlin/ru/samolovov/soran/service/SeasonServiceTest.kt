package ru.samolovov.soran.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.SeasonDto
import ru.samolovov.soran.dto.TeamDto
import ru.samolovov.soran.dto.TournamentDto

@Transactional
class SeasonServiceTest(
    @Autowired
    private val seasonService: SeasonService,
    @Autowired
    private val tournamentService: TournamentService,
    @Autowired
    private val teamService: TeamService
) : IntegrationTests() {

    lateinit var teams: List<TeamDto>
    lateinit var tournament: TournamentDto

    @BeforeEach
    internal fun setUp() {
        tournament = tournamentService.create(buildTournament("Осеннее первенство"))
        teams = listOf(
            teamService.create(buildTeam("Геология")),
            teamService.create(buildTeam("Сборная НГУ")),
            teamService.create(buildTeam("Академ 2.0"))
        )
    }

    @Test
    fun `test create season`() {
        val notCreated = buildNew()
        val created = seasonService.create(notCreated)

        Assertions.assertThat(created.id).isNotNull()
        assertEquals(created, notCreated)
    }

    @Test
    fun `test update season`() {
        val created = seasonService.create(buildNew())
        val notUpdated = buildUpdated()

        val updated = seasonService.update(created.id!!, notUpdated)

        Assertions.assertThat(updated.id).isEqualTo(created.id!!)
        assertEquals(updated, notUpdated)
    }

    @Test
    fun `test load by id`() {
        val created = seasonService.create(buildNew())
        val loaded = seasonService.loadById(created.id!!)

        Assertions.assertThat(loaded.id).isEqualTo(created.id!!)
        assertEquals(loaded, created)
    }

    private fun assertEquals(first: SeasonDto, second: SeasonDto) {
        Assertions.assertThat(first.tournamentId).isEqualTo(second.tournamentId)
        Assertions.assertThat(first.teamIds).isEqualTo(second.teamIds)
        Assertions.assertThat(first.startDate).isEqualTo(second.startDate)
        Assertions.assertThat(first.endDate).isEqualTo(second.endDate)
    }

    private fun buildNew() = buildSeason(tournament.id!!, setOf(teams[0].id!!, teams[1].id!!))

    private fun buildUpdated() = buildSeason(tournament.id!!, setOf(teams[1].id!!, teams[2].id!!))
}