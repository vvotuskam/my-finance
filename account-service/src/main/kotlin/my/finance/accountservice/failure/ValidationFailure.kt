package my.finance.accountservice.failure

class ValidationFailure(
    private val field: String,
    private val cause: String,
    override val code: Int = 400,
    override val message: String = "field `$field` is invalid. $cause"
) : Failure