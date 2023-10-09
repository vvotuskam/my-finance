package my.finance.accountservice.validator

import org.springframework.validation.BindingResult

interface Validator {

    fun validate(result: BindingResult)
}