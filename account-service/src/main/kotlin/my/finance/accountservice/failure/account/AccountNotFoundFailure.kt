package my.finance.accountservice.failure.account

import my.finance.accountservice.failure.Failure

data class AccountNotFoundFailure(
    override val code: Int = 404,
    override val message: String = "Account not found"
): Failure