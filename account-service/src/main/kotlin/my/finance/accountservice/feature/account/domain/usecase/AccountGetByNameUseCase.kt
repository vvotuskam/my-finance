package my.finance.accountservice.feature.account.domain.usecase

import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.account.rest.dto.response.AccountFullResponse
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.feature.account.domain.failure.AccountNotFoundFailure
import my.finance.accountservice.core.domain.usecase.UseCase
import org.springframework.stereotype.Component

@Component
class AccountGetByNameUseCase(
    private val accountService: AccountService
): UseCase<AccountGetByNameUseCase.AccountGetByIdParams, AccountFullResponse> {

    data class AccountGetByIdParams(
        val name: String
    )

    override fun invoke(params: AccountGetByIdParams): AccountFullResponse {
        val account = accountService.findByName(params.name)
            ?: throw BusinessException(AccountNotFoundFailure())

        return AccountFullResponse(
            id = account.id!!,
            name = account.name,
            amount = account.amount
        )
    }
}