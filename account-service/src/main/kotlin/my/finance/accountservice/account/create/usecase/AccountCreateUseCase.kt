package my.finance.accountservice.account.create.usecase

import my.finance.accountservice.account.Account
import my.finance.accountservice.account.AccountService
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase.AccountCreateParams
import my.finance.accountservice.exception.BusinessException
import my.finance.accountservice.failure.account.AccountAlreadyExistsFailure
import my.finance.accountservice.failure.user.UserNotFoundFailure
import my.finance.accountservice.success.SuccessResponse
import my.finance.accountservice.usecase.UseCase
import my.finance.accountservice.user.User
import my.finance.accountservice.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.util.UUID

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

        val account = accountService.findByName(name)

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