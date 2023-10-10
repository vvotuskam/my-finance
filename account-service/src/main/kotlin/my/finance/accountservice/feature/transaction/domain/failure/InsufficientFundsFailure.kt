package my.finance.accountservice.feature.transaction.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class InsufficientFundsFailure(
    override val code: Int = 400,
    override val message: String = "Insufficient amount of funds"
): Failure