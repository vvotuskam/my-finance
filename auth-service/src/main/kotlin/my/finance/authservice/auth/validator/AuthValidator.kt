package my.finance.authservice.auth.validator

import my.finance.authservice.base.Validator
import my.finance.authservice.base.failure.ValidationFailure
import my.finance.authservice.exception.BusinessException
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class AuthValidator : Validator {

    companion object {
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val DEFAULT_CAUSE = "Field is null, empty"
    }

    override fun validate(result: BindingResult) {
        throw when {
            result.hasFieldErrors(EMAIL) ->
                BusinessException(
                    failure = ValidationFailure(
                        field = EMAIL,
                        cause = DEFAULT_CAUSE
                    )
                )
            result.hasFieldErrors(PASSWORD) ->
                BusinessException(
                    failure = ValidationFailure(
                        field = EMAIL,
                        cause = DEFAULT_CAUSE
                    )
                )
            else -> return
        }
    }
}