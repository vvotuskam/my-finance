package my.finance.accountservice.account.getbyname.validator

import my.finance.accountservice.exception.BusinessException
import my.finance.accountservice.failure.ValidationFailure
import my.finance.accountservice.validator.Validator
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