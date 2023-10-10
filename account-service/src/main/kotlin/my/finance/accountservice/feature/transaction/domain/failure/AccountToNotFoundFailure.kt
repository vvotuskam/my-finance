package my.finance.accountservice.feature.transaction.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class AccountToNotFoundFailure(
    override val code: Int = 404,
    override val message: String = "Account `to` not found"
) : Failure