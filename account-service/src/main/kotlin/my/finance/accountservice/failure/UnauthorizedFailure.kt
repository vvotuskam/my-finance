package my.finance.accountservice.failure

data class UnauthorizedFailure(
    override val code: Int = 401,
    override val message: String = "unauthorized",
) : Failure