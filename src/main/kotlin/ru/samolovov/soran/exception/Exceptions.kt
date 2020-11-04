package ru.samolovov.soran.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class TeamNotFoundException(id: Long) : RuntimeException("Team with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class TeamsNotFoundException(ids: Set<Long>) : RuntimeException("Teams with ids=$ids not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class PlayerNotFoundException(id: Long) : RuntimeException("Player with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class PlayersNotFoundException(ids: Set<Long>) : RuntimeException("Players with ids=$ids not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class GameNotFoundException(id: Long) : RuntimeException("Game with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class GameDetailsNotFoundException(id: Long) : RuntimeException("Game details with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class RefereeNotFoundException(id: Long) : RuntimeException("Referee with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class TournamentNotFoundException(id: Long) : RuntimeException("Tournament with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class SeasonNotFoundException(id: Long) : RuntimeException("Season with id=$id not found")

@ResponseStatus(HttpStatus.NOT_FOUND)
class SeasonTeamNotFoundException(seasonId: Long, teamId: Long) : RuntimeException(
    "Season team with seasonId=$seasonId and teamId=$teamId not found"
)