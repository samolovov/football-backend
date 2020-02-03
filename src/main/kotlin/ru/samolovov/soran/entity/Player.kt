package ru.samolovov.soran.entity

import ru.samolovov.soran.dto.Position
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "players")
class Player(
    @Column(name = "first_name")
    var firstName: String,
    @Column(name = "last_name")
    var lastName: String,
    @Column(name = "birthday")
    var birthday: LocalDate? = null,
    @Column(name = "avatar")
    var avatar: String? = null,
    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    var position: Position? = null
) : BaseEntity()

