package my.finance.accountservice.feature.account.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class AccountAlreadyExistsFailure(
    override val code: Int = 400,
    override val message: String = "Account already exists"
) : Failure