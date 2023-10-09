package my.finance.authservice.base.failure

interface Failure {
    val code: Int
    val message: String
}