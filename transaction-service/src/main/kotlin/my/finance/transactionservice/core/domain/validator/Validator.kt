package my.finance.transactionservice.core.domain.validator

import org.springframework.validation.BindingResult

interface Validator {

    fun validate(result: BindingResult)
}