package my.finance.authservice.feature.registration.domain.failure

import my.finance.authservice.core.domain.failure.base.Failure

data class CodeRevokedFailure(
    override val code: Int = 400,
    override val message: String = "OTP code is expired or revoked",
) : Failure