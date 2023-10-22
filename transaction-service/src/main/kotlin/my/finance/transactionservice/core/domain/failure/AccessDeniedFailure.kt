package my.finance.transactionservice.core.domain.failure

import my.finance.transactionservice.core.domain.failure.base.Failure

data class AccessDeniedFailure(
    override val code: Int = 403,
    override val message: String = "Access denied"
): Failure
