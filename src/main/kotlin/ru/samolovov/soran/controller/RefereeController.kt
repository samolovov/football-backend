package ru.samolovov.soran.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.samolovov.soran.dto.RefereeDto
import ru.samolovov.soran.service.RefereeService
import javax.validation.Valid

@RestController
@RequestMapping("/referees")
class RefereeController(
    private val refereeService: RefereeService
) {
    @PostMapping
    fun create(@Valid @RequestBody referee: RefereeDto) = refereeService.create(referee)

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody referee: RefereeDto) =
        refereeService.update(id, referee)

    @GetMapping("/{id}")
    fun loadOne(@PathVariable("id") id: Long) = refereeService.loadById(id)

    @GetMapping
    fun loadAll() = refereeService.loadAll()
}