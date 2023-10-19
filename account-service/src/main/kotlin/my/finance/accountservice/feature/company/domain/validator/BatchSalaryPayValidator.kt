package my.finance.accountservice.feature.company.domain.validator

import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.ValidationFailure
import my.finance.accountservice.core.domain.validator.CompositeValidator
import my.finance.accountservice.feature.company.rest.dto.request.BatchSalaryPayRequest
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class BatchSalaryPayValidator : CompositeValidator<BatchSalaryPayRequest> {

    companion object {
        private const val COMPANY_ID = "companyId"
        private const val COMPANY_ID_MESSAGE = "Invalid Company ID"
    }

    override fun validate(request: BatchSalaryPayRequest) {
        try {
            UUID.fromString(request.companyId)
        } catch (e: Exception) {
            throw BusinessException(
                failure = ValidationFailure(
                    field = COMPANY_ID,
                    cause = COMPANY_ID_MESSAGE
                )
            )
        }
    }
}