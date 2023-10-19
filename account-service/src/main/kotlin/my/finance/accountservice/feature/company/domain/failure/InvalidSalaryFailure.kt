package my.finance.accountservice.feature.company.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class InvalidSalaryFailure(
    override val code: Int = 400,
    override val message: String = "Invalid salary"
) : Failure