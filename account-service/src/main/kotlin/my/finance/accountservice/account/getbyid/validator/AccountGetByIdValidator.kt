package my.finance.accountservice.account.getbyid.validator

import my.finance.accountservice.exception.BusinessException
import my.finance.accountservice.failure.ValidationFailure
import my.finance.accountservice.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class AccountGetByIdValidator : Validator {

    companion object {
        private const val ID = "id"
    }

    override fun validate(result: BindingResult) {
        throw when {
            result.hasFieldErrors(ID) ->
                BusinessException(
                    failure = ValidationFailure(
                        field = ID,
                        cause = "Field is null, empty, or not uuid"
                    )
                )
            else -> return
        }
    }
}