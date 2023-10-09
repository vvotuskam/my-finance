package my.finance.accountservice.exception

import my.finance.accountservice.failure.Failure

data class BusinessException(
    val failure: Failure
) : RuntimeException()