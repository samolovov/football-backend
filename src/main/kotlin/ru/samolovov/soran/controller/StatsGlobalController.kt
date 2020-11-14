package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samolovov.soran.service.StatsService

@RestController
@RequestMapping("/stats/global")
class StatsGlobalController(
    private val statsService: StatsService
) {

    @GetMapping("/players")
    fun getForAllPlayers() = statsService.getForAllPlayers()
}