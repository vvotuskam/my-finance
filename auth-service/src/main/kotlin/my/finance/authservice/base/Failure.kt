package my.finance.authservice.base

interface Failure {
    val code: Int
    val message: String
}