package ru.samolovov.soran.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import ru.samolovov.soran.dto.Type
import javax.persistence.FetchType

@Entity
@Table(name = "game_details")
open class GameDetails(
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    val game: Game,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    var team: Team,

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    var player: Player,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    var type: Type,

    @Column(name = "minute")
    var minute: Int
) : BaseEntity()