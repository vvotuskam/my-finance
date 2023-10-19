package my.finance.accountservice.feature.account.domain.usecase

import my.finance.accountservice.core.domain.noparams.NoParams
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.account.rest.dto.response.AccountFullResponse
import my.finance.accountservice.feature.account.rest.dto.response.AccountGetResponse
import org.springframework.stereotype.Component

@Component
class AccountGetUseCase(
    private val accountService: AccountService,
) : UseCase<NoParams, AccountGetResponse> {

    override fun invoke(params: NoParams): AccountGetResponse {
        val accounts = accountService.findAll()
            .map { AccountFullResponse(
                id = it.id!!,
                name = it.name,
                amount = it.amount
            ) }
        return AccountGetResponse(
            accounts = accounts
        )
    }

}