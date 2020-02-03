package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.*
import ru.samolovov.soran.dto.PlayerDto
import ru.samolovov.soran.service.PlayerService
import javax.validation.Valid

@RestController
@RequestMapping("/players")
class PlayerController(
    private val playerService: PlayerService
) {
    @PostMapping
    fun create(@Valid @RequestBody player: PlayerDto) = playerService.create(player)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody player: PlayerDto) = playerService.update(id, player)

    @GetMapping("/{id}")
    fun loadOne(@PathVariable("id") id: Long) = playerService.loadById(id)

    @GetMapping
    fun loadAll() = playerService.loadAll()
}