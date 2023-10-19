package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.Account
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.company.data.company.Company
import my.finance.accountservice.feature.company.data.company.CompanyService
import my.finance.accountservice.feature.company.data.salary.SalaryTransaction
import my.finance.accountservice.feature.company.data.salary.SalaryTransactionService
import my.finance.accountservice.feature.company.domain.failure.CompanyNotFoundFailure
import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase.BatchSalaryParams
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class BatchSalaryPayUseCase(
    private val companyService: CompanyService,
    private val accountService: AccountService,
    private val transactionService: SalaryTransactionService
): UseCase<BatchSalaryParams, SuccessResponse> {

    data class BatchSalaryParams(
        val admin: User,
        val companyId: UUID
    )

    @Transactional
    override fun invoke(params: BatchSalaryParams): SuccessResponse {
        val (admin, companyId) = params

        val company = companyService.findById(companyId)
            ?: throw BusinessException(CompanyNotFoundFailure())

        if (company.admin.id != admin.id) throw BusinessException(CompanyNotFoundFailure())

        val paidAccounts = ArrayList<Account>()

        for (employee in company.employees) {
            val paidAccount = employee.account.copy(
                amount = employee.account.amount + employee.salary
            )
            paidAccounts.add(paidAccount)
        }
        accountService.saveAll(paidAccounts)

        val transactions = company.employees
            .map {
                SalaryTransaction(
                    amount = it.salary,
                    company = company,
                    employee = it,
                    transferredBy = admin
                )
            }

        transactionService.saveAll(transactions)
        return SuccessResponse("Salaries paid")
    }
}