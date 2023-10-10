package my.finance.authservice.core.domain.failure

import my.finance.authservice.core.domain.failure.base.Failure

data class ValidationFailure(
    private val field: String,
    private val cause: String,
    override val code: Int = 400,
    override val message: String = "Field `$field` is invalid. $cause".trim(),
) : Failure