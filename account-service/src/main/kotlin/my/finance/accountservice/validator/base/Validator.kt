package my.finance.accountservice.validator.base

import org.springframework.validation.BindingResult

interface Validator {

    fun validate(result: BindingResult)
}