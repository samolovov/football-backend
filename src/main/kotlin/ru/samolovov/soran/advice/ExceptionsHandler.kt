package ru.samolovov.soran.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import ru.samolovov.soran.exception.NotFoundException
import java.time.LocalDateTime

@RestControllerAdvice
class ExceptionsHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun notFound(ex: NotFoundException, request: WebRequest) = ErrorMessage(
        status = HttpStatus.NOT_FOUND,
        message = ex.message,
        description = request.getDescription(false)
    )

    class ErrorMessage(
        val status: HttpStatus,
        val date: LocalDateTime = LocalDateTime.now(),
        val message: String?,
        val description: String
    )
}

