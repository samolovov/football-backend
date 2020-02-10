package ru.samolovov.soran.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.samolovov.soran.dto.TournamentDto
import ru.samolovov.soran.entity.Tournament
import ru.samolovov.soran.exception.TournamentNotFoundException
import ru.samolovov.soran.repository.TournamentRepository

@Service
@Transactional
class TournamentService(
    private val tournamentRepository: TournamentRepository
) {
    fun create(tournamentDto: TournamentDto) = tournamentRepository.save(
        with(tournamentDto) {
            Tournament(
                name = name
            )
        }
    ).toTournamentDto()

    fun update(id: Long, tournamentDto: TournamentDto): TournamentDto {
        val tournament = tournamentRepository.findByIdOrNull(id) ?: throw TournamentNotFoundException(id)
        tournament.update(tournamentDto)
        return tournamentRepository.save(tournament).toTournamentDto()
    }

    fun loadById(id: Long): TournamentDto {
        return tournamentRepository.findByIdOrNull(id)?.toTournamentDto() ?: throw TournamentNotFoundException(id)
    }

    fun loadAll() = tournamentRepository.findAll().map { it.toTournamentDto() }
}

private fun Tournament.update(tournamentDto: TournamentDto) {
    this.name = tournamentDto.name
}

private fun Tournament.toTournamentDto() = TournamentDto(
    id = id,
    name = name
)