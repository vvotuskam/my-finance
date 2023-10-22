package my.finance.transactionservice.core.domain.validator

interface CompositeValidator<REQUEST> {

    fun validate(request: REQUEST)
}