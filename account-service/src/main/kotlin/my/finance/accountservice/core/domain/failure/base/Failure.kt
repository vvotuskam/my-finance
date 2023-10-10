package my.finance.accountservice.core.domain.failure.base

interface Failure {
    val code: Int
    val message: String
}