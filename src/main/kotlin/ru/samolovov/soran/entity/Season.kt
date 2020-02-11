package ru.samolovov.soran.entity

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "seasons")
class Season(
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    val tournament: Tournament,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
        name = "season_teams",
        joinColumns = [JoinColumn(name = "season_id")],
        inverseJoinColumns = [JoinColumn(name = "team_id")]
    )
    var teams: Set<Team> = emptySet(),

    @Column(name = "start_date")
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate
) : BaseEntity()