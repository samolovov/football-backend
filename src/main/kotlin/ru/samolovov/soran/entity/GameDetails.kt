package ru.samolovov.soran.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import ru.samolovov.soran.dto.Type

@Entity
@Table(name = "game_details")
class GameDetails(
    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    val game: Game,

    @ManyToOne(optional = false)
    @JoinColumn(name = "team_id")
    var team: Team,

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id")
    var player: Player,

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    var type: Type,

    @Column(name = "minute")
    var minute: Int
) : BaseEntity()