package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.*
import ru.samolovov.soran.dto.TournamentDto
import ru.samolovov.soran.service.SeasonService
import ru.samolovov.soran.service.TournamentService
import javax.validation.Valid

@RestController
@RequestMapping("/tournaments")
class TournamentController(
    private val tournamentService: TournamentService,
    private val seasonService: SeasonService
) {
    @PostMapping
    fun create(@Valid @RequestBody tournament: TournamentDto) = tournamentService.create(tournament)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody tournament: TournamentDto) =
        tournamentService.update(id, tournament)

    @GetMapping("/{id}")
    fun loadOne(@PathVariable("id") id: Long) = tournamentService.loadById(id)

    @GetMapping
    fun loadAll() = tournamentService.loadAll()

    @GetMapping("/{id}/seasons")
    fun loadSeasons(@PathVariable("id") id: Long) = seasonService.loadByTournamentId(id)
}