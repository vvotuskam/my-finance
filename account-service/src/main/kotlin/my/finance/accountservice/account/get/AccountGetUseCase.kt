package my.finance.accountservice.account.get

import my.finance.accountservice.account.AccountService
import my.finance.accountservice.account.get.dto.AccountGetResponse
import my.finance.accountservice.account.getbyid.dto.AccountGetByIdResponse
import my.finance.accountservice.usecase.NoParams
import my.finance.accountservice.usecase.UseCase
import org.springframework.stereotype.Component

@Component
class AccountGetUseCase(
    private val accountService: AccountService,
) : UseCase<NoParams, AccountGetResponse> {

    override fun invoke(params: NoParams): AccountGetResponse {
        val accounts = accountService.findAll()
            .map { AccountGetByIdResponse(
                id = it.id!!,
                name = it.name,
                amount = it.amount
            ) }
        return AccountGetResponse(
            accounts = accounts
        )
    }

}