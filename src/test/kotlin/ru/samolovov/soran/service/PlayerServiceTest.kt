package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.PlayerDto
import ru.samolovov.soran.dto.Position

@Transactional
internal class PlayerServiceTest(
    @Autowired
    private val playerService: PlayerService
): IntegrationTests() {

    @Test
    fun `test create player`() {
        val notCreated = buildNew()
        val created = playerService.create(notCreated)

        assertThat(created.id).isNotNull()
        assertEquals(created, notCreated)
    }

    @Test
    fun `test update player`() {
        val created = playerService.create(buildNew())
        val notUpdated = buildUpdated()

        val updated = playerService.update(created.id!!, notUpdated)

        assertThat(updated.id).isEqualTo(created.id!!)
        assertEquals(updated, notUpdated)
    }

    @Test
    fun `test load by id`() {
        val created = playerService.create(buildNew())
        val loaded = playerService.loadById(created.id!!)

        assertThat(loaded.id).isEqualTo(created.id!!)
        assertEquals(loaded, created)
    }

    private fun assertEquals(first: PlayerDto, second: PlayerDto) {
        assertThat(first.firstName).isEqualTo(second.firstName)
        assertThat(first.lastName).isEqualTo(second.lastName)
        assertThat(first.avatar).isEqualTo(second.avatar)
        assertThat(first.position).isEqualTo(second.position)
        assertThat(first.birthday).isEqualTo(second.birthday)
    }

    private fun buildNew() = buildPlayer("Pavel", "Samolovov", Position.MIDFIELDER)
    private fun buildUpdated() = buildPlayer("Andrey", "Arshavin", Position.FORWARD)
}