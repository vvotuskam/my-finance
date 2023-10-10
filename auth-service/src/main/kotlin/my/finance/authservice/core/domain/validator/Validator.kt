package my.finance.authservice.core.domain.validator

import org.springframework.validation.BindingResult

interface Validator {

    fun validate(result: BindingResult)
}