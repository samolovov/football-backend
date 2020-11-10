package ru.samolovov.soran.entity

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "games")
open class Game(
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    var season: Season,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "first_team_id")
    var firstTeam: Team,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "second_team_id")
    var secondTeam: Team,

    @Column(name = "first_team_goals")
    var firstTeamGoals: Int,

    @Column(name = "second_team_goals")
    var secondTeamGoals: Int,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "referee_id")
    var referee: Referee,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "game")
    var details: MutableSet<GameDetails> = mutableSetOf()
) : BaseEntity()