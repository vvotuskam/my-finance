package my.finance.transactionservice.feature.salary.domain.validator

import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class SalaryTransactionValidator : Validator {

    companion object {
        private const val COMPANY_ID = "companyId"
        private const val EMPLOYEES = "employees"

        private const val UUID_MESSAGE = "Field is null, empty or not UUID"
        private const val DEFAULT_MESSAGE = "Field is null or empty"
    }

    override fun validate(result: BindingResult) {
        if (result.hasFieldErrors(COMPANY_ID))
            throw BusinessException(ValidationFailure(COMPANY_ID, UUID_MESSAGE))

        if (result.hasFieldErrors(EMPLOYEES))
            throw BusinessException(ValidationFailure(EMPLOYEES, DEFAULT_MESSAGE))
    }
}