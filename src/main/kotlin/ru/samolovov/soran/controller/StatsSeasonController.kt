package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samolovov.soran.service.StatsService

@RestController
@RequestMapping("/stats/season/{seasonId}")
class StatsSeasonController(
    private val statsService: StatsService
) {

    @GetMapping("/players")
    fun getForAllPlayers(@PathVariable("seasonId") seasonId: Long) = statsService.getForAllPlayers(seasonId)

    @GetMapping("/teams")
    fun getForAllTeams(@PathVariable("seasonId") seasonId: Long) = statsService.getForAllTeams(seasonId)
}