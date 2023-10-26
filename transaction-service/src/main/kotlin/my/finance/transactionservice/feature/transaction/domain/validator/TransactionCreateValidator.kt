package my.finance.transactionservice.feature.transaction.domain.validator

import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class TransactionCreateValidator : Validator {

    companion object {
        private const val ACCOUNT_ID = "accountId"
        private const val SECOND_ID = "secondId"
        private const val AMOUNT = "amount"
        private const val IS_POSITIVE = "isPositive"
        private const val DESCRIPTION = "description"

        private const val DEFAULT_MESSAGE = "Field is null or empty"
        private const val UUID_MESSAGE = "Field is null, empty, or not UUID"
        private const val AMOUNT_MESSAGE = "Field is null, empty, or less than 100"
    }

    override fun validate(result: BindingResult) {
        val failure = when {
            result.hasFieldErrors(ACCOUNT_ID) -> ValidationFailure(ACCOUNT_ID, UUID_MESSAGE)
            result.hasFieldErrors(SECOND_ID) -> ValidationFailure(SECOND_ID, UUID_MESSAGE)
            result.hasFieldErrors(AMOUNT) -> ValidationFailure(AMOUNT, AMOUNT_MESSAGE)
            result.hasFieldErrors(IS_POSITIVE) -> ValidationFailure(IS_POSITIVE, DEFAULT_MESSAGE)
            result.hasFieldErrors(DESCRIPTION) -> ValidationFailure(DESCRIPTION, DEFAULT_MESSAGE)
            else -> null
        }

        if (failure != null) throw BusinessException(failure)
    }
}