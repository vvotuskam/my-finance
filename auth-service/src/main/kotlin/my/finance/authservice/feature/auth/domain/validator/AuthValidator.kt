package my.finance.authservice.feature.auth.domain.validator

import my.finance.authservice.core.domain.validator.Validator
import my.finance.authservice.core.domain.failure.ValidationFailure
import my.finance.authservice.core.domain.exception.BusinessException
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class AuthValidator : Validator {

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
    }

    override fun validate(result: BindingResult) {
        throw when {
            result.hasFieldErrors(EMAIL) ->
                BusinessException(
                    failure = ValidationFailure(
                        field = EMAIL,
                        cause = "Field is null, empty, or not email"
                    )
                )
            result.hasFieldErrors(PASSWORD) ->
                BusinessException(
                    failure = ValidationFailure(
                        field = PASSWORD,
                        cause = "Field is null, empty"
                    )
                )
            else -> return
        }
    }
}