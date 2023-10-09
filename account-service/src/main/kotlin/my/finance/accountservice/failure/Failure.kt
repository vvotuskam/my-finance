package my.finance.accountservice.failure

interface Failure {
    val code: Int
    val message: String
}