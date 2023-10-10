package my.finance.authservice.base.failure

data class BadCredentialsFailure(
    override val code: Int = 401,
    override val message: String = "Incorrect email or password"
): Failure