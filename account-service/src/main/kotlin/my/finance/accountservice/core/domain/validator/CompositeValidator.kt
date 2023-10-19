package my.finance.accountservice.core.domain.validator

interface CompositeValidator<REQUEST> {

    fun validate(request: REQUEST)
}