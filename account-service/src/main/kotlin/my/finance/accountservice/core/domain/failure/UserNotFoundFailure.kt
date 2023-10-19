package my.finance.accountservice.core.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class UserNotFoundFailure(
    override val code: Int = 404,
    override val message: String = "User not found"
) : Failure