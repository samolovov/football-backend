package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.samolovov.soran.dto.PlayerDto
import ru.samolovov.soran.entity.Player
import ru.samolovov.soran.exception.PlayerNotFoundException
import ru.samolovov.soran.repository.PlayerRepository
import javax.transaction.Transactional

@Service
@Transactional
class PlayerService(
    private val playerRepository: PlayerRepository
) {
    fun create(playerDto: PlayerDto) = playerRepository.save(
        with(playerDto) {
            Player(
                firstName = firstName,
                lastName = lastName,
                avatar = avatar,
                birthday = birthday,
                position = position
            )
        }).toPlayerDto()

    fun update(id: Long, playerDto: PlayerDto): PlayerDto {
        val player = playerRepository.findByIdOrNull(id) ?: throw PlayerNotFoundException(id)
        player.update(playerDto)
        return playerRepository.save(player).toPlayerDto()
    }

    fun loadById(id: Long) =
        playerRepository.findByIdOrNull(id)?.toPlayerDto() ?: throw PlayerNotFoundException(id)

    fun loadAll() = playerRepository.findAll().map { it.toPlayerDto() }
}

private fun Player.update(playerDto: PlayerDto) {
    this.firstName = playerDto.firstName
    this.lastName = playerDto.lastName
    this.avatar = playerDto.avatar
    this.birthday = playerDto.birthday
    this.position = playerDto.position

}

private fun Player.toPlayerDto() = PlayerDto(
    id = id,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar,
    birthday = birthday,
    position = position
)