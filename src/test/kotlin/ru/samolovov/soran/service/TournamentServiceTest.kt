package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.TournamentDto

@Transactional
internal class TournamentServiceTest(
    @Autowired
    private val tournamentService: TournamentService
): IntegrationTests() {

    @Test
    fun `test create tournament`() {
        val notCreated = buildNew()
        val created = tournamentService.create(notCreated)

        assertThat(created.id).isNotNull()
        assertEquals(created, notCreated)
    }

    @Test
    fun `test update tournament`() {
        val created = tournamentService.create(buildNew())
        val notUpdated = buildUpdated()
        val updated = tournamentService.update(created.id!!, notUpdated)

        assertThat(updated.id).isEqualTo(created.id!!)
        assertEquals(updated, notUpdated)
    }

    @Test
    fun `test load by id`() {
        val created = tournamentService.create(buildNew())
        val loaded = tournamentService.loadById(created.id!!)

        assertThat(loaded.id).isEqualTo(created.id!!)
        assertEquals(loaded, created)
    }

    private fun assertEquals(first: TournamentDto, second: TournamentDto) {
        assertThat(first.name).isEqualTo(second.name)
    }

    private fun buildNew() = buildTournament("Осенний СОРАН")
    private fun buildUpdated() = buildTournament("Осеннее первенство СОРАН")
}