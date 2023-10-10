package my.finance.accountservice.core.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class AccessDeniedFailure(
    override val code: Int = 403,
    override val message: String = "Access denied"
): Failure
