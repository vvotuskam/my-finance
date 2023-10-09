package my.finance.authservice.base

import org.springframework.validation.BindingResult

interface Validator {

    fun validate(result: BindingResult)
}