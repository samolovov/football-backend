package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import ru.samolovov.soran.dto.TeamDto
import ru.samolovov.soran.entity.Team
import ru.samolovov.soran.exception.TeamNotFoundException
import ru.samolovov.soran.repository.TeamRepository
import javax.transaction.Transactional

@Service
@Transactional
class TeamService(
    private val teamRepository: TeamRepository
) {
    fun create(teamDto: TeamDto) = teamRepository.save(
        with(teamDto) {
            Team(
                name = name,
                birthday = birthday,
                description = description,
                avatar = avatar
            )
        }
    ).toTeamDto()

    fun update(id: Long, teamDto: TeamDto): TeamDto {
        val team = teamRepository.findByIdOrNull(id) ?: throw TeamNotFoundException(id)
        team.update(teamDto)
        return teamRepository.save(team).toTeamDto()
    }

    fun loadById(id: Long): TeamDto {
        return teamRepository.findByIdOrNull(id)?.toTeamDto() ?: throw TeamNotFoundException(id)
    }

    fun loadAll() = teamRepository.findAll().map { it.toTeamDto() }
}

private fun Team.update(teamDto: TeamDto) {
    this.name = teamDto.name
    this.birthday = teamDto.birthday
    this.avatar = teamDto.avatar
    this.description = teamDto.description
}

internal fun Team.toTeamDto() = TeamDto(
    id = id,
    birthday = birthday,
    name = name,
    avatar = avatar,
    description = description
)