package my.finance.accountservice.account.getbyname.usecase

import my.finance.accountservice.account.AccountService
import my.finance.accountservice.account.getbyname.dto.AccountGetByNameResponse
import my.finance.accountservice.exception.BusinessException
import my.finance.accountservice.failure.account.AccountNotFoundFailure
import my.finance.accountservice.usecase.UseCase
import org.springframework.stereotype.Component

@Component
class AccountGetByNameUseCase(
    private val accountService: AccountService
): UseCase<AccountGetByNameUseCase.AccountGetByIdParams, AccountGetByNameResponse> {

    data class AccountGetByIdParams(
        val name: String
    )

    override fun invoke(params: AccountGetByIdParams): AccountGetByNameResponse {
        val account = accountService.findByName(params.name)
            ?: throw BusinessException(AccountNotFoundFailure())

        return AccountGetByNameResponse(
            id = account.id!!,
            name = account.name,
            amount = account.amount
        )
    }
}