package ru.samolovov.soran.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "season_teams")
class SeasonTeam(
    @ManyToOne
    @JoinColumn(name = "season_id")
    val season: Season,

    @ManyToOne
    @JoinColumn(name = "team_id")
    val team: Team,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "season_team_players",
        joinColumns = [JoinColumn(name = "season_team_id")],
        inverseJoinColumns = [JoinColumn(name = "player_id")]
    )
    var players: Set<Player> = emptySet()
) : BaseEntity()