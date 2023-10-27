package my.finance.transactionservice.feature.salary.domain.validator

import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.validator.CompositeValidator
import my.finance.transactionservice.feature.salary.rest.dto.request.CompanySalaryRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class CompanySalaryValidator : CompositeValidator<CompanySalaryRequest> {

    companion object {
        private const val COMPANY_ID = "companyId"
        private const val UUID_MESSAGE = "Field is null, empty, or not UUID"
    }

    override fun validate(request: CompanySalaryRequest) {
        try {
            UUID.fromString(request.companyId)
        } catch (e: Exception) {
            throw BusinessException(ValidationFailure(COMPANY_ID, UUID_MESSAGE))
        }
    }
}