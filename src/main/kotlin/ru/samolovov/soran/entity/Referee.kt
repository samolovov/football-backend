package ru.samolovov.soran.entity

import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "referees")
class Referee(
    @Column(name = "first_name")
    var firstName: String,
    @Column(name = "last_name")
    var lastName: String,
    @Column(name = "birthday")
    var birthday: LocalDate? = null,
    @Column(name = "avatar")
    var avatar: String? = null
) : BaseEntity()