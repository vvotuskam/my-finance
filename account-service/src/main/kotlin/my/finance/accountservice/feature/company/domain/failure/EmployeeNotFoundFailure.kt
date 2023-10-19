package my.finance.accountservice.feature.company.domain.failure

import my.finance.accountservice.core.domain.failure.base.Failure

data class EmployeeNotFoundFailure(
    override val code: Int = 404,
    override val message: String = "Employee not found"
) : Failure