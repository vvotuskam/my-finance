package my.finance.accountservice.core.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class UnauthorizedFailure(
    override val code: Int = 401,
    override val message: String = "unauthorized",
) : Failure