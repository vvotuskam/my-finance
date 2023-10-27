package my.finance.accountservice.feature.transaction.domain.validator

import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.ValidationFailure
import my.finance.accountservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class TransferValidator : Validator {

    companion object {
        private const val FROM = "from"
        private const val TO = "to"
        private const val AMOUNT = "amount"
        private const val DESCRIPTION = "description"

        private const val DEFAULT_MESSAGE = "Field is null or empty"
        private const val UUID_MESSAGE = "Field is null, empty, or not uuid"
        private const val AMOUNT_MESSAGE = "Field is null or less than 100"
    }

    override fun validate(result: BindingResult) {
        val failure = when {
            result.hasFieldErrors(FROM) -> ValidationFailure(FROM, UUID_MESSAGE)
            result.hasFieldErrors(TO) -> ValidationFailure(TO, UUID_MESSAGE)
            result.hasFieldErrors(AMOUNT) -> ValidationFailure(AMOUNT, AMOUNT_MESSAGE)
            result.hasFieldErrors(DESCRIPTION) -> ValidationFailure(DESCRIPTION, DEFAULT_MESSAGE)
            else -> null
        }

        if (failure != null) throw BusinessException(failure)
    }
}