package my.finance.accountservice.feature.transaction.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.feature.account.data.Account
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.account.domain.failure.AccountNotFoundFailure
import my.finance.accountservice.feature.account.rest.dto.response.AccountResponse
import my.finance.accountservice.feature.transaction.data.TransactionService
import my.finance.accountservice.feature.transaction.rest.dto.response.TransactionResponse
import org.springframework.stereotype.Component

@Component
class HistoryUseCase(
    private val transactionService: TransactionService,
    private val accountService: AccountService,
) : UseCase<HistoryUseCase.HistoryParams, List<TransactionResponse>> {

    data class HistoryParams(
        val user: User,
        val name: String,
    )

    override fun invoke(params: HistoryParams): List<TransactionResponse> {
        val (user, name) = params

        val account = accountService.findByNameAndUser(name, user)
            ?: throw BusinessException(AccountNotFoundFailure())

        return transactionService.findAllByAccount(account)
            .map {
                TransactionResponse(
                    id = it.id!!,
                    accountFrom = accountToResponse(it.from),
                    accountTo = accountToResponse(it.to),
                    amount = it.amount
                )
            }
    }

    private fun accountToResponse(account: Account): AccountResponse {
        return AccountResponse(
            id = account.id!!,
            name = account.name,
            owner = account.user.email
        )
    }
}