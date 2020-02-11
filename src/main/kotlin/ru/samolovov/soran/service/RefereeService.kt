package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.samolovov.soran.dto.RefereeDto
import ru.samolovov.soran.entity.Referee
import ru.samolovov.soran.exception.RefereeNotFoundException
import ru.samolovov.soran.repository.RefereeRepository
import javax.transaction.Transactional

@Service
@Transactional
class RefereeService(
    private val refereeRepository: RefereeRepository
) {
    fun create(refereeDto: RefereeDto) = refereeRepository.save(
        with(refereeDto) {
            Referee(
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                birthday = birthday
            )
        }).toRefereeDto()

    fun update(id: Long, refereeDto: RefereeDto): RefereeDto {
        val referee = refereeRepository.findByIdOrNull(id) ?: throw RefereeNotFoundException(id)
        referee.update(refereeDto)
        return refereeRepository.save(referee).toRefereeDto()
    }

    fun loadById(id: Long) =
        refereeRepository.findByIdOrNull(id)?.toRefereeDto() ?: throw RefereeNotFoundException(id)

    fun loadAll() = refereeRepository.findAll().map { it.toRefereeDto() }
}

private fun Referee.update(refereeDto: RefereeDto) {
    this.firstName = refereeDto.firstName
    this.lastName = refereeDto.lastName
    this.avatar = refereeDto.avatar
    this.birthday = refereeDto.birthday

}

internal fun Referee.toRefereeDto() = RefereeDto(
    id = id,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar,
    birthday = birthday
)