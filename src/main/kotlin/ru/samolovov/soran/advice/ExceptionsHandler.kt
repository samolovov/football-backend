package ru.samolovov.soran.advice

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun constraintViolation(ex: DataIntegrityViolationException, request: WebRequest) = ErrorMessage(
        status = HttpStatus.CONFLICT,
        message = ex.message,
        description = request.getDescription(false)
    )

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(
        e: MethodArgumentNotValidException,
        request: WebRequest
    ): ErrorMessage {
        val errors = e.bindingResult.allErrors.map {
            val fieldName = (it as FieldError).field
            val errorMessage = it.defaultMessage
            "$fieldName: $errorMessage"
        }
        return ErrorMessage(
            status = HttpStatus.BAD_REQUEST,
            message = errors.toString(),
            description = request.getDescription(false)
        )
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun internalServerError(ex: Exception, request: WebRequest) = ErrorMessage(
        status = HttpStatus.INTERNAL_SERVER_ERROR,
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

