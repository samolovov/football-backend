package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samolovov.soran.dto.GameDetailsDto
import ru.samolovov.soran.service.GameDetailsService
import javax.validation.Valid

@RestController
@RequestMapping("/games/{gameId}/details")
class GameDetailsController(
    private val gameDetailsService: GameDetailsService
) {
    @PostMapping
    fun create(
        @PathVariable("gameId") gameId: Long,
        @Valid @RequestBody gameDetails: GameDetailsDto
    ) = gameDetailsService.create(gameId, gameDetails)

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @Valid @RequestBody gameDetails: GameDetailsDto
    ) = gameDetailsService.update(id, gameDetails)

    @DeleteMapping("/{id}")
    fun delete(
        @PathVariable("id") id: Long
    ) = gameDetailsService.delete(id)

}