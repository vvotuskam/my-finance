package my.finance.authservice.feature.registration.domain.validator

import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.failure.ValidationFailure
import my.finance.authservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class CodeValidator : Validator {

    companion object {
        private const val CODE = "code"
        private const val CODE_MESSAGE = "Field is null, empty, or not OTP"
    }

    override fun validate(result: BindingResult) {
        if (result.hasFieldErrors(CODE))
            throw BusinessException(
                failure = ValidationFailure(
                    field = CODE,
                    cause = CODE_MESSAGE
                )
            )
    }
}