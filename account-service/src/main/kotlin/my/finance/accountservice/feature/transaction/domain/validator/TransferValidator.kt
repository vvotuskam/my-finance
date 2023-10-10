package my.finance.accountservice.feature.transaction.domain.validator

import my.finance.accountservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class TransferValidator : Validator {

    override fun validate(result: BindingResult) {
        TODO("Not yet implemented")
    }
}