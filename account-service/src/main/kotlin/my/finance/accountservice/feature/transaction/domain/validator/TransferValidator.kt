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

        private const val FROM_MESSAGE = "Field is null, empty, or not uuid"
        private const val TO_MESSAGE = "Field is null, empty, or not uuid"
        private const val AMOUNT_MESSAGE = "Field is null or less than 100"
    }

    override fun validate(result: BindingResult) {
        val failure = when {
            result.hasFieldErrors(FROM) -> ValidationFailure(FROM, FROM_MESSAGE)
            result.hasFieldErrors(TO) -> ValidationFailure(TO, TO_MESSAGE)
            result.hasFieldErrors(AMOUNT) -> ValidationFailure(AMOUNT, AMOUNT_MESSAGE)
            else -> null
        }

        if (failure != null) throw BusinessException(failure)
    }
}