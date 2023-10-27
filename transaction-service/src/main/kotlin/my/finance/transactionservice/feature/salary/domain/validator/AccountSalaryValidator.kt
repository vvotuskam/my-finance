package my.finance.transactionservice.feature.salary.domain.validator

import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.validator.CompositeValidator
import my.finance.transactionservice.feature.salary.rest.dto.request.AccountSalaryRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountSalaryValidator : CompositeValidator<AccountSalaryRequest> {

    companion object {
        private const val ACCOUNT_ID = "accountId"
        private const val UUID_MESSAGE = "Field is null, empty, or not UUID"
    }

    override fun validate(request: AccountSalaryRequest) {
        try {
            UUID.fromString(request.accountId)
        } catch (e: Exception) {
            throw BusinessException(ValidationFailure(ACCOUNT_ID, UUID_MESSAGE))
        }
    }
}