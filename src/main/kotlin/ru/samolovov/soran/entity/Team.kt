package ru.samolovov.soran.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "teams")
open class Team(
    @Column(name = "name", unique = true)
    var name: String,
    @Column(name = "birthday")
    var birthday: LocalDate? = null,
    @Column(name = "description")
    var description: String? = null,
    @Column(name = "avatar")
    var avatar: String? = null
) : BaseEntity()