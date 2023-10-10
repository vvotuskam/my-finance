package my.finance.accountservice.feature.account.domain.validator

import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.ValidationFailure
import my.finance.accountservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class AccountCreateValidator : Validator {

    companion object {
        private const val NAME = "name"
        private const val USER_ID = "userId"
    }

    override fun validate(result: BindingResult) {

        if (result.hasFieldErrors(NAME))
            throw BusinessException(
                ValidationFailure(NAME, "Field is null or empty")
            )
    }
}