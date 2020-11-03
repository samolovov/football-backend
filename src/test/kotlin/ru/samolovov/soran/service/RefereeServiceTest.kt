package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.RefereeDto

@Transactional
internal class RefereeServiceTest(
    @Autowired
    private val refereeService: RefereeService
): IntegrationTests() {

    @Test
    fun `test create referee`() {
        val notCreated = buildNew()
        val created = refereeService.create(notCreated)

        assertThat(created.id).isNotNull()
        assertEquals(created, notCreated)
    }

    @Test
    fun `test update referee`() {
        val created = refereeService.create(buildNew())
        val notUpdated = buildUpdated()

        val updated = refereeService.update(created.id!!, notUpdated)

        assertThat(updated.id).isEqualTo(created.id!!)
        assertEquals(updated, notUpdated)
    }

    @Test
    fun `test load by id`() {
        val created = refereeService.create(buildNew())
        val loaded = refereeService.loadById(created.id!!)

        assertThat(loaded.id).isEqualTo(created.id!!)
        assertEquals(loaded, created)
    }

    private fun assertEquals(first: RefereeDto, second: RefereeDto) {
        assertThat(first.firstName).isEqualTo(second.firstName)
        assertThat(first.lastName).isEqualTo(second.lastName)
        assertThat(first.avatar).isEqualTo(second.avatar)
        assertThat(first.birthday).isEqualTo(second.birthday)
    }

    private fun buildNew() = buildReferee("Sergey", "Karasev")
    private fun buildUpdated() = buildReferee("Markus", "Merk")
}