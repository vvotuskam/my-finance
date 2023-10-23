package my.finance.transactionservice.feature.transaction.domain.validator

import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class TransactionHistoryValidator : Validator {

    companion object {
        private const val ACCOUNT_ID = "accountId"
        private const val UUID_MESSAGE = "Field is null, empty, or not UUD"
    }

    override fun validate(result: BindingResult) {
        if (result.hasFieldErrors(ACCOUNT_ID))
            throw BusinessException(
                failure = ValidationFailure(
                    field = ACCOUNT_ID,
                    cause = UUID_MESSAGE
                )
            )
    }
}