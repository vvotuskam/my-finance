package my.finance.accountservice.feature.company.domain.validator

import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.ValidationFailure
import my.finance.accountservice.core.domain.validator.CompositeValidator
import my.finance.accountservice.feature.company.rest.dto.request.SalaryPayRequest
import org.hibernate.validator.constraints.UUID
import org.springframework.stereotype.Component

@Component
class SalaryPayValidator : CompositeValidator<SalaryPayRequest> {

    companion object {
        private const val COMPANY_ID = "companyId"
        private const val EMPLOYEE_ID = "employeeId"

        private const val DEFAULT_MESSAGE = "Invalid ID"

        private val UUID_REGEX = Regex("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[1-5][0-9a-fA-F]{3}-[89abAB][0-9a-fA-F]{3}-[0-9a-fA-F]{12}\$")
    }

    override fun validate(request: SalaryPayRequest) {
        val failure = when {
            !UUID_REGEX.matches(request.companyId) -> ValidationFailure(COMPANY_ID, DEFAULT_MESSAGE)
            !UUID_REGEX.matches(request.employeeId) -> ValidationFailure(EMPLOYEE_ID, DEFAULT_MESSAGE)
            else -> null
        }

        if (failure != null) throw BusinessException(failure)
    }
}