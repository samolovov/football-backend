package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samolovov.soran.dto.GameRequestDto
import ru.samolovov.soran.service.GameService
import javax.validation.Valid

@RestController
@RequestMapping("/games")
class GameController(
    private val gameService: GameService
) {
    @PostMapping
    fun create(@Valid @RequestBody game: GameRequestDto) =
        gameService.create(game)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody game: GameRequestDto) = gameService.update(id, game)

    @GetMapping("/{id}")
    fun loadOne(@PathVariable("id") id: Long) = gameService.loadById(id)

    @GetMapping
    fun loadAll() = gameService.loadAll()
}