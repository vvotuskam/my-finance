package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.company.domain.usecase.SalaryPayUseCase.SalaryPayParams
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SalaryPayUseCase(

): UseCase<SalaryPayParams, SuccessResponse> {

    data class SalaryPayParams(
        val admin: User,
        val employeeId: UUID,
        val companyId: UUID
    )

    override fun invoke(params: SalaryPayParams): SuccessResponse {
        TODO("Not yet implemented")
    }
}