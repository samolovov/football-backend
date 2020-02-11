package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.*
import ru.samolovov.soran.dto.SeasonRequestDto
import ru.samolovov.soran.service.SeasonService
import javax.validation.Valid

@RestController
@RequestMapping("/seasons")
class SeasonController(
    private val seasonService: SeasonService
) {
    @PostMapping
    fun create(@Valid @RequestBody season: SeasonRequestDto) = seasonService.create(season)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody season: SeasonRequestDto) =
        seasonService.update(id, season)

    @GetMapping("/{id}")
    fun loadOne(@PathVariable("id") id: Long) = seasonService.loadById(id)

    @GetMapping
    fun loadAll() = seasonService.loadAll()
}