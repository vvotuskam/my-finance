package my.finance.accountservice.failure.user

import my.finance.accountservice.failure.Failure

data class UserNotFoundFailure(
    override val code: Int = 400,
    override val message: String = "user not found"
) : Failure