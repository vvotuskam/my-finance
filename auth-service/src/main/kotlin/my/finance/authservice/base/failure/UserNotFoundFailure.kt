package my.finance.authservice.base.failure

import my.finance.authservice.base.failure.Failure

data class UserNotFoundFailure(
    override val code: Int = 404,
    override val message: String = "User not found"
) : Failure