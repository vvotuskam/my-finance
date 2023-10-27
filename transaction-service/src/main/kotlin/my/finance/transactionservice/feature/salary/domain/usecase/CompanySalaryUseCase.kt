package my.finance.transactionservice.feature.salary.domain.usecase

import my.finance.transactionservice.core.domain.usecase.UseCase
import my.finance.transactionservice.feature.salary.data.SalaryTransactionService
import my.finance.transactionservice.feature.salary.domain.usecase.CompanySalaryUseCase.CompanySalaryParams
import my.finance.transactionservice.feature.salary.rest.dto.response.SalaryResponse
import my.finance.transactionservice.feature.salary.rest.dto.response.SalaryTransactionResponse
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CompanySalaryUseCase(
    private val salaryService: SalaryTransactionService
) : UseCase<CompanySalaryParams, SalaryTransactionResponse> {

    data class CompanySalaryParams(
        val companyId: UUID,
        val admin: String
    )

    override fun invoke(params: CompanySalaryParams): SalaryTransactionResponse {
        val transactions = salaryService.findByCompanyIdAndAdmin(
            params.companyId, params.admin
        )
        return SalaryTransactionResponse(
            transactions = transactions.map {
                SalaryResponse(
                    id = it.id!!,
                    transferredAt = it.transferredAt,
                    email = it.email,
                    admin = it.admin,
                    accountId = it.accountId,
                    companyId = it.companyId,
                    amount = it.amount
                )
            }
        )
    }
}