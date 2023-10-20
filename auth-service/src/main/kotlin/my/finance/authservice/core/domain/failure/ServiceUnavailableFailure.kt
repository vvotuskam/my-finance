package my.finance.authservice.core.domain.failure

import my.finance.authservice.core.domain.failure.base.Failure

data class ServiceUnavailableFailure(
    override val code: Int = 500,
    override val message: String = "Service unavailable"
): Failure