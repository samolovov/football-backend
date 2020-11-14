package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samolovov.soran.service.StatsService

@RestController
@RequestMapping("/stats/global")
class StatsGlobalController(
    private val statsService: StatsService
) {

    @GetMapping("/referees")
    fun getForAllReferees() = statsService.getForAllReferees()

    @GetMapping("/player/{playerId}")
    fun getForPlayer(@PathVariable("playerId") playerId: Long) = statsService.getForPlayer(playerId)

    @GetMapping("/players")
    fun getForAllPlayers() = statsService.getForAllPlayers()

    @GetMapping("/team/{teamId}")
    fun getForTeam(@PathVariable("teamId") teamId: Long) = statsService.getForTeam(teamId)

    @GetMapping("/teams")
    fun getForAllTeams() = statsService.getForAllTeams()
}