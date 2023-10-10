package my.finance.authservice.feature.registration.domain.failure

import my.finance.authservice.core.domain.failure.base.Failure

data class EmailExistsFailure(
    override val code: Int = 403,
    override val message: String = "Email already registered"
): Failure