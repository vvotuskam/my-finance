package my.finance.authservice.exception

import my.finance.authservice.base.failure.Failure

data class BusinessException(
    val failure: Failure,
) : RuntimeException()