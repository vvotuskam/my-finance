package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.company.data.company.Company
import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase.BatchSalaryParams
import org.springframework.stereotype.Component

@Component
class BatchSalaryPayUseCase(

): UseCase<BatchSalaryParams, SuccessResponse> {

    data class BatchSalaryParams(
        val admin: User,
        val company: Company
    )

    override fun invoke(params: BatchSalaryParams): SuccessResponse {
        TODO("Not yet implemented")
    }
}