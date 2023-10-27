package my.finance.transactionservice.feature.salary.domain.validator

import jakarta.validation.Validator
import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.validator.CompositeValidator
import my.finance.transactionservice.feature.salary.rest.dto.request.SalaryTransactionRequest
import org.springframework.stereotype.Component

@Component
class SalaryEmployeesValidator(
    private val validator: Validator
) : CompositeValidator<SalaryTransactionRequest> {

    companion object {
        private const val SALARY = "salary"
        private const val ACCOUNT_ID = "accountId"
        private const val EMAIL = "email"

        private const val UUID_MESSAGE = "Field is null, empty or not UUID"
        private const val AMOUNT_MESSAGE = "Field is null, empty or less than 1"
        private const val EMAIL_MESSAGE = "Field is null, empty or not email"
    }

    override fun validate(request: SalaryTransactionRequest) {
        request.employees?.forEach { employee ->
            val violations = validator.validate(employee)

            if (violations.any { it.propertyPath.toString() == SALARY })
                throw BusinessException(ValidationFailure(SALARY, AMOUNT_MESSAGE))

            if (violations.any { it.propertyPath.toString() == ACCOUNT_ID })
                throw BusinessException(ValidationFailure(ACCOUNT_ID, UUID_MESSAGE))

            if (violations.any { it.propertyPath.toString() == EMAIL })
                throw BusinessException(ValidationFailure(EMAIL, EMAIL_MESSAGE))
        }
    }
}