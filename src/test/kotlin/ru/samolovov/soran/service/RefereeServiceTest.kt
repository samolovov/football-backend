package ru.samolovov.soran.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.IntegrationTests
import ru.samolovov.soran.dto.RefereeDto
import java.time.LocalDate

@Transactional
internal class RefereeServiceTest(
    @Autowired
    private val refereeService: RefereeService
): IntegrationTests() {

    @Test
    fun `test create referee`() {
        val notCreated = createRefereeDto()
        val created = refereeService.create(notCreated)

        assertThat(created.id).isNotNull()
        assertEquals(created, notCreated)
    }

    @Test
    fun `test update referee`() {
        val created = refereeService.create(createRefereeDto())

        val notUpdated = RefereeDto(
            firstName = "Sergey",
            lastName = "Karasev",
            avatar = "karasev.png",
            birthday = LocalDate.of(1982, 9, 3)
        )

        val updated = refereeService.update(created.id!!, notUpdated)

        assertThat(updated.id).isEqualTo(created.id!!)
        assertEquals(updated, notUpdated)
    }

    @Test
    fun `test load by id`() {
        val created = refereeService.create(createRefereeDto())
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

    private fun createRefereeDto() = RefereeDto(
        firstName = "Pier Luidzhi",
        lastName = "Kollina",
        avatar = "kollina.png",
        birthday = LocalDate.of(1970, 4, 12)
    )
}