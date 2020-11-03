package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.TeamDto
import java.time.LocalDate

@Transactional
internal class TeamServiceTest(
    @Autowired
    private val teamService: TeamService
) : IntegrationTests() {

    @Test
    fun `test create team`() {
        val notCreated = buildNew()
        val created = teamService.create(notCreated)

        assertThat(created.id).isNotNull()
        assertEquals(created, notCreated)
    }

    @Test
    fun `test update team`() {
        val created = teamService.create(buildNew())
        val notUpdated = buildUpdated()

        val updated = teamService.update(created.id!!, notUpdated)

        assertThat(updated.id).isEqualTo(created.id!!)
        assertEquals(updated, notUpdated)
    }

    @Test
    fun `test load by id`() {
        val created = teamService.create(buildNew())
        val loaded = teamService.loadById(created.id!!)

        assertThat(loaded.id).isEqualTo(created.id!!)
        assertEquals(loaded, created)
    }

    private fun assertEquals(first: TeamDto, second: TeamDto) {
        assertThat(first.name).isEqualTo(second.name)
        assertThat(first.avatar).isEqualTo(second.avatar)
        assertThat(first.description).isEqualTo(second.description)
        assertThat(first.birthday).isEqualTo(second.birthday)
    }

    private fun buildNew() = buildTeam("Джокер")
    private fun buildUpdated() = buildTeam("Первомаец")
}