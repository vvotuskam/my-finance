package my.finance.accountservice.account.getbyid.usecase

import my.finance.accountservice.account.AccountService
import my.finance.accountservice.account.getbyid.dto.AccountGetByIdResponse
import my.finance.accountservice.exception.BusinessException
import my.finance.accountservice.failure.account.AccountNotFoundFailure
import my.finance.accountservice.usecase.UseCase
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountGetByIdUseCase(
    private val accountService: AccountService
): UseCase<AccountGetByIdUseCase.AccountGetByIdParams, AccountGetByIdResponse> {

    data class AccountGetByIdParams(
        val id: UUID
    )

    override fun invoke(params: AccountGetByIdParams): AccountGetByIdResponse {
        val account = accountService.findById(id = params.id)
            ?: throw BusinessException(AccountNotFoundFailure())

        return AccountGetByIdResponse(
            id = account.id!!,
            name = account.name,
            amount = account.amount
        )
    }
}