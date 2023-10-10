package my.finance.authservice.core.domain.failure.base

interface Failure {
    val code: Int
    val message: String
}