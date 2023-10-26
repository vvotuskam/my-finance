package my.finance.authservice.feature.registration.domain.failure

import my.finance.authservice.core.domain.failure.base.Failure

data class UserAlreadyExistsFailure(
    override val code: Int = 409,
    override val message: String = "User already exists"
): Failure