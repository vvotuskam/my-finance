package my.finance.accountservice.feature.account.domain.validator

import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.ValidationFailure
import my.finance.accountservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class AccountGetByNameValidator : Validator {

    companion object {
        private const val NAME = "name"
    }

    override fun validate(result: BindingResult) {
        throw when {
            result.hasFieldErrors(NAME) ->
                BusinessException(
                    failure = ValidationFailure(
                        field = NAME,
                        cause = "Field is null or empty"
                    )
                )
            else -> return
        }
    }
}