package my.finance.accountservice.feature.transaction.domain.usecase

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.account.domain.failure.AccountNotFoundFailure
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionHistoryRequest
import my.finance.accountservice.feature.transaction.domain.source.response.TransactionHistoryResponse
import my.finance.accountservice.feature.transaction.domain.source.service.TransactionService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class HistoryUseCase(
    private val accountService: AccountService,
    private val transactionService: TransactionService
) : UseCase<HistoryUseCase.HistoryParams, TransactionHistoryResponse> {

    data class HistoryParams(
        val email: String,
        val name: String,
    )

    override fun invoke(params: HistoryParams): TransactionHistoryResponse {
        val (email, name) = params

        val account = accountService.findByNameAndEmail(name, email)
            ?: throw BusinessException(AccountNotFoundFailure())

        val authentication = SecurityContextHolder.getContext().authentication
        val securityUser = authentication.principal as SecurityUser

        val request = TransactionHistoryRequest(account.id!!)

        return transactionService.history(request, securityUser)
    }
}