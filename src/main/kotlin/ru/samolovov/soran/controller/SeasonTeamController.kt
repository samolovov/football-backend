package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samolovov.soran.dto.SeasonTeamDto
import ru.samolovov.soran.service.SeasonTeamService
import javax.validation.Valid

@RestController
@RequestMapping("/season/{seasonId}/teams")
class SeasonTeamController(
    private val seasonTeamService: SeasonTeamService
) {

    @PutMapping("/{teamId}")
    fun update(
        @PathVariable("seasonId") seasonId: Long,
        @PathVariable("teamId") teamId: Long,
        @Valid @RequestBody seasonTeam: SeasonTeamDto
    ) =
        seasonTeamService.update(seasonId, teamId, seasonTeam)
}