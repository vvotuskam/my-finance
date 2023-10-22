package my.finance.transactionservice.core.domain.failure.base

interface Failure {
    val code: Int
    val message: String
}