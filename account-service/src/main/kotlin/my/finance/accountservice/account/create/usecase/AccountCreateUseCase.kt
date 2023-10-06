package my.finance.accountservice.account.create.usecase

import my.finance.accountservice.account.Account
import my.finance.accountservice.account.AccountService
import my.finance.accountservice.user.UserService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class AccountCreateUseCase(
    private val accountService: AccountService,
    private val userService: UserService,
) {

    data class AccountCreateParams(
        val name: String,
        val userId: UUID
    )

    fun invoke(params: AccountCreateParams): String {
        val (name, userId) = params

        val user = userService.findById(userId) ?: return "User not found"

        val isUniqueName = accountService.isUniqueName(name, user)

        if (!isUniqueName) return "Already exists"

        val account = Account(
            name = params.name,
            amount = 0.0,
            user = user
        )

        accountService.save(account)

        return "Created"
    }
}