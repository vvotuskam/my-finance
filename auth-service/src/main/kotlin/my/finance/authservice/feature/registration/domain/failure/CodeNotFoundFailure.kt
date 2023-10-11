package my.finance.authservice.feature.registration.domain.failure

import my.finance.authservice.core.domain.failure.base.Failure

data class CodeNotFoundFailure(
    override val code: Int = 404,
    override val message: String = "OTP code not found"
): Failure