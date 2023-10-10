package my.finance.authservice.feature.registration.domain.validator

import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.failure.ValidationFailure
import my.finance.authservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class EmailValidator : Validator {

    companion object {
        private const val EMAIL = "email"
        private const val EMAIL_MESSAGE = "Field is null, empty, or not email"
    }

    override fun validate(result: BindingResult) {
        if (result.hasFieldErrors(EMAIL))
            throw BusinessException(
                failure = ValidationFailure(
                    field = EMAIL,
                    cause = EMAIL_MESSAGE
                )
            )
    }
}