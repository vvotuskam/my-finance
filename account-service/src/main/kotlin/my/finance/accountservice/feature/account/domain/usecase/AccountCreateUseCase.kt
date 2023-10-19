package my.finance.accountservice.feature.account.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.Account
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.account.domain.failure.AccountAlreadyExistsFailure
import my.finance.accountservice.feature.account.domain.usecase.AccountCreateUseCase.AccountCreateParams
import org.springframework.stereotype.Component

@Component
class AccountCreateUseCase(
    private val accountService: AccountService,
): UseCase<AccountCreateParams, SuccessResponse> {

    data class AccountCreateParams(
        val name: String,
        val user: User
    )

    override fun invoke(params: AccountCreateParams): SuccessResponse {
        val (name, user) = params

        val account = accountService.findByNameAndUser(name, user)

        if (account != null) throw BusinessException(AccountAlreadyExistsFailure())

        val newAccount = Account(
            name = params.name,
            amount = 0.0,
            user = user
        )

        accountService.save(newAccount)

        return SuccessResponse(status = "Created")
    }
}