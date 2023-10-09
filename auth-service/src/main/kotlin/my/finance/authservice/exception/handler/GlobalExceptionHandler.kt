package my.finance.authservice.exception.handler

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import my.finance.authservice.base.failure.Failure
import my.finance.authservice.base.failure.ValidationFailure
import my.finance.authservice.exception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<Failure> {
        val failure = e.failure
        return ResponseEntity.status(failure.code).body(failure)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageException(
        e: HttpMessageNotReadableException,
    ): ResponseEntity<Failure> {
        val mismatchedInputException = e.cause as MismatchedInputException
        val cause = "Deserialization failure"
        val field = mismatchedInputException.path[0].fieldName

        val failure = ValidationFailure(field = field, cause = cause)
        return ResponseEntity.status(failure.code).body(failure)
    }
}