package my.finance.accountservice.failure

class ValidationFailure(
    private val field: String,
    override val code: Int = 400,
    override val message: String = "field `$field` is invalid"
) : Failure