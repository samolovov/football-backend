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

    @GetMapping("/scorers")
    fun getScorers(@PathVariable("seasonId") seasonId: Long) = statsService.getScorers(seasonId)

    @GetMapping("/teams")
    fun getAllTeamStats(@PathVariable("seasonId") seasonId: Long) = statsService.getAllTeamStats(seasonId)
}