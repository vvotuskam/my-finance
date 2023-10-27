package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.Account
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.company.data.company.CompanyService
import my.finance.accountservice.feature.company.domain.failure.CompanyNotFoundFailure
import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase.BatchSalaryParams
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionBatchCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.service.TransactionService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Component
class BatchSalaryPayUseCase(
    private val companyService: CompanyService,
    private val accountService: AccountService,
    private val transactionService: TransactionService
): UseCase<BatchSalaryParams, SuccessResponse> {

    data class BatchSalaryParams(
        val admin: String,
        val companyId: UUID
    )

    @Transactional
    override fun invoke(params: BatchSalaryParams): SuccessResponse {
        val (admin, companyId) = params

        val company = companyService.findById(companyId)
            ?: throw BusinessException(CompanyNotFoundFailure())

        if (company.admin != admin) throw BusinessException(CompanyNotFoundFailure())

        val paidAccounts = ArrayList<Account>()
        val transactions = ArrayList<TransactionCreateRequest>()

        for (employee in company.employees) {
            val paidAccount = employee.account.copy(
                amount = employee.account.amount + employee.salary
            )
            val transaction = TransactionCreateRequest(
                accountId = employee.account.id!!,
                secondId = companyId,
                amount = employee.salary,
                isPositive = true,
                email = employee.email,
                description = "Salary from '${company.name}'"
            )

            transactions.add(transaction)
            paidAccounts.add(paidAccount)
        }

        val authentication = SecurityContextHolder.getContext().authentication
        val securityUser = authentication.principal as SecurityUser
        val batchTransactions = TransactionBatchCreateRequest(transactions)

        transactionService.batchCreate(batchTransactions, securityUser)
        accountService.saveAll(paidAccounts)

        return SuccessResponse("Salaries paid")
    }
}