package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.company.domain.usecase.RegisterEmployeeUseCase.*
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class RegisterEmployeeUseCase(

): UseCase<RegisterParams, SuccessResponse> {

    data class RegisterParams(
        val name: String,
        val surname: String,
        val accountId: UUID,
        val userId: UUID,
        val companyId: UUID
    )

    override fun invoke(params: RegisterParams): SuccessResponse {
        TODO("Not yet implemented")
    }
}