package ru.samolovov.soran.validation

import ru.samolovov.soran.dto.DateRange
import java.time.LocalDate
import javax.validation.Constraint
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import javax.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.CONSTRUCTOR, AnnotationTarget.CLASS)
@MustBeDocumented
@Constraint(validatedBy = [ConsistentDatesValidator::class])
annotation class ConsistentDates(
    val message: String = "startDate must be before endDate and both dates must be in future",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class ConsistentDatesValidator :
    ConstraintValidator<ConsistentDates, DateRange> {

    override fun isValid(
        dateRange: DateRange,
        context: ConstraintValidatorContext?
    ) = dateRange.startDate > LocalDate.now() && dateRange.endDate > dateRange.startDate
}