package ru.samolovov.soran.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "tournaments")
open class Tournament(
    @Column(name = "name")
    var name: String
) : BaseEntity()