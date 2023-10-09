package my.finance.accountservice.failure.account

import my.finance.accountservice.failure.Failure

data class AccountAlreadyExistsFailure(
    override val code: Int = 400,
    override val message: String = "Account already exists"
) : Failure