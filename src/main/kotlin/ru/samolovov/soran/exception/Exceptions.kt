package ru.samolovov.soran.exception

open class NotFoundException(message: String) : RuntimeException(message)

class TeamNotFoundException(id: Long) : NotFoundException("Team with id=$id not found")

class TeamsNotFoundException(ids: Set<Long>) : NotFoundException("Teams with ids=$ids not found")

class PlayerNotFoundException(id: Long) : NotFoundException("Player with id=$id not found")

class PlayersNotFoundException(ids: Set<Long>) : NotFoundException("Players with ids=$ids not found")

class GameNotFoundException(id: Long) : NotFoundException("Game with id=$id not found")

class GameDetailsNotFoundException(id: Long) : NotFoundException("Game details with id=$id not found")

class RefereeNotFoundException(id: Long) : NotFoundException("Referee with id=$id not found")

class TournamentNotFoundException(id: Long) : NotFoundException("Tournament with id=$id not found")

class SeasonNotFoundException(id: Long) : NotFoundException("Season with id=$id not found")

class SeasonTeamNotFoundException(seasonId: Long, teamId: Long) : RuntimeException(
    "Season team with seasonId=$seasonId and teamId=$teamId not found"
)