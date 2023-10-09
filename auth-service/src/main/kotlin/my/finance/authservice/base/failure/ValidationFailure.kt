package my.finance.authservice.base.failure

data class ValidationFailure(
    private val field: String,
    private val cause: String,
    override val code: Int = 400,
    override val message: String = "Field `$field` is invalid. $cause".trim(),
) : Failure