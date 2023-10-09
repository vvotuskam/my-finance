package my.finance.accountservice.validator

import my.finance.accountservice.exception.BusinessException
import my.finance.accountservice.failure.ValidationFailure
import my.finance.accountservice.validator.base.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class AccountCreateValidator : Validator {

    companion object {
        private const val NAME = "name"
        private const val USER_ID = "userId"
    }

    override fun validate(result: BindingResult) {

        throw when {
            result.hasFieldErrors(NAME) -> BusinessException(ValidationFailure(NAME))
            result.hasFieldErrors(USER_ID) -> BusinessException(ValidationFailure(USER_ID))
            else -> return
        }
    }
}