package my.finance.authservice.core.domain.exception

import my.finance.authservice.core.domain.failure.base.Failure

data class BusinessException(
    val failure: Failure,
) : RuntimeException()