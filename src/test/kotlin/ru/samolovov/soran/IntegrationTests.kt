package ru.samolovov.soran

import org.springframework.boot.test.context.SpringBootTest
import ru.samolovov.soran.dto.GameDetailsDto
import ru.samolovov.soran.dto.GameDto
import ru.samolovov.soran.dto.PlayerDto
import ru.samolovov.soran.dto.Position
import ru.samolovov.soran.dto.RefereeDto
import ru.samolovov.soran.dto.SeasonDto
import ru.samolovov.soran.dto.SeasonTeamDto
import ru.samolovov.soran.dto.TeamDto
import ru.samolovov.soran.dto.TournamentDto
import ru.samolovov.soran.dto.Type
import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class IntegrationTests {
    protected fun buildGameDetails(
        gameId: Long,
        teamId: Long,
        playerId: Long,
        type: Type,
        minute: Int = Random.nextInt(0, 60)
    ) = GameDetailsDto(
        gameId = gameId,
        teamId = teamId,
        playerId = playerId,
        type = type,
        minute = minute
    )

    protected fun buildGame(
        seasonId: Long,
        firstTeamId: Long,
        secondTeamId: Long,
        firstTeamGoals: Int,
        secondTeamGoals: Int,
        refereeId: Long
    ) = GameDto(
        seasonId = seasonId,
        firstTeamId = firstTeamId,
        secondTeamId = secondTeamId,
        firstTeamGoals = firstTeamGoals,
        secondTeamGoals = secondTeamGoals,
        refereeId = refereeId,
        date = randomDate()
    )

    protected fun buildSeasonTeam(seasonId: Long, teamId: Long, playerIds: Set<Long>) =
        SeasonTeamDto(
            seasonId = seasonId,
            teamId = teamId,
            playerIds = playerIds
        )

    protected fun buildSeason(tournamentId: Long, teamIds: Set<Long>) = SeasonDto(
        tournamentId = tournamentId,
        teamIds = teamIds,
        startDate = randomDate(),
        endDate = randomDate()
    )

    protected fun buildTeam(name: String) = TeamDto(
        name = name,
        avatar = randomString(),
        description = randomString(),
        birthday = randomDate()
    )

    protected fun buildReferee(firstName: String, lastName: String) = RefereeDto(
        firstName = firstName,
        lastName = lastName,
        avatar = randomString(),
        birthday = randomDate()
    )

    protected fun buildTournament(name: String) = TournamentDto(name = name)

    protected fun buildPlayer(firstName: String, lastName: String, position: Position) = PlayerDto(
        firstName = firstName,
        lastName = lastName,
        position = position,
        avatar = randomString(),
        birthday = randomDate()
    )

    protected fun randomString() = UUID.randomUUID().toString()
    protected fun randomDate() = LocalDate.of(
        Random.nextInt(1970, 2020),
        Random.nextInt(1, 12),
        Random.nextInt(1, 28)
    )
}