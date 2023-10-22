package my.finance.transactionservice.core.domain.handler

import com.fasterxml.jackson.databind.exc.MismatchedInputException
import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ServiceUnavailableFailure
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.failure.base.Failure
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import kotlin.Exception

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(e: Exception): ResponseEntity<Failure> {
        val failure = ServiceUnavailableFailure()
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

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException) : ResponseEntity<Failure> {
        val failure = e.failure
        return ResponseEntity.status(failure.code).body(failure)
    }

}