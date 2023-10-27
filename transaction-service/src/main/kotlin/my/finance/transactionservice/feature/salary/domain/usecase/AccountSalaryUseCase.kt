package my.finance.transactionservice.feature.salary.domain.usecase

import my.finance.transactionservice.core.domain.usecase.UseCase
import my.finance.transactionservice.feature.salary.data.SalaryTransactionService
import my.finance.transactionservice.feature.salary.domain.usecase.AccountSalaryUseCase.AccountSalaryParams
import my.finance.transactionservice.feature.salary.rest.dto.response.SalaryResponse
import my.finance.transactionservice.feature.salary.rest.dto.response.SalaryTransactionResponse
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AccountSalaryUseCase(
    private val salaryService: SalaryTransactionService
) : UseCase<AccountSalaryParams, SalaryTransactionResponse> {

    data class AccountSalaryParams(
        val accountId: UUID,
        val email: String,
    )

    override fun invoke(params: AccountSalaryParams): SalaryTransactionResponse {
        val transactions = salaryService.findByAccountAndEmail(
            params.accountId, params.email
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