package my.finance.accountservice.core.domain.exception

import my.finance.accountservice.core.domain.failure.base.Failure

data class BusinessException(
    val failure: Failure
) : RuntimeException()