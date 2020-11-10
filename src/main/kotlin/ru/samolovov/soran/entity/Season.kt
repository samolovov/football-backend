package ru.samolovov.soran.entity

import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "seasons")
open class Season(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    val tournament: Tournament,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "season")
    var teams: MutableSet<SeasonTeam> = mutableSetOf(),

    @Column(name = "start_date")
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, mappedBy = "season")
    var games: MutableSet<Game> = mutableSetOf()
) : BaseEntity()