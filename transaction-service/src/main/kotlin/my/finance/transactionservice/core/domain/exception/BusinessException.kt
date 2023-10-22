package my.finance.transactionservice.core.domain.exception

import my.finance.transactionservice.core.domain.failure.base.Failure

data class BusinessException(
    val failure: Failure
) : RuntimeException()