package my.finance.authservice.core.domain.failure

import my.finance.authservice.core.domain.failure.base.Failure

data class BadCredentialsFailure(
    override val code: Int = 401,
    override val message: String = "Incorrect email or password"
): Failure