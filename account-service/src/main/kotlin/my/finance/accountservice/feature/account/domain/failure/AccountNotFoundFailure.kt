package my.finance.accountservice.feature.account.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class AccountNotFoundFailure(
    override val code: Int = 404,
    override val message: String = "Account not found"
): Failure