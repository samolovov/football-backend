package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.*
import ru.samolovov.soran.dto.TeamDto
import ru.samolovov.soran.service.TeamService
import javax.validation.Valid

@RestController
@RequestMapping("/teams")
class TeamController(
    private val teamService: TeamService
) {
    @PostMapping
    fun create(@Valid @RequestBody team: TeamDto) = teamService.create(team)

    @PutMapping("/teams/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody team: TeamDto) = teamService.update(id, team)

    @GetMapping("/teams/{id}")
    fun loadOne(@PathVariable("id") id: Long) = teamService.loadById(id)

    @GetMapping
    fun loadAll() = teamService.loadAll()
}