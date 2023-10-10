package my.finance.accountservice.failure

data class AccessDeniedFailure(
    override val code: Int = 403,
    override val message: String = "Access denied"
): Failure
