package my.finance.transactionservice.feature.transaction.domain.validator

import jakarta.validation.Validator
import my.finance.transactionservice.core.domain.exception.BusinessException
import my.finance.transactionservice.core.domain.failure.ValidationFailure
import my.finance.transactionservice.core.domain.validator.CompositeValidator
import my.finance.transactionservice.feature.transaction.rest.dto.request.TransactionBatchCreateRequest
import org.springframework.stereotype.Component
import org.springframework.validation.BeanPropertyBindingResult

@Component
class TransactionBatchCreateValidator(
    val validator: Validator,
    val createValidator: TransactionCreateValidator,
) : CompositeValidator<TransactionBatchCreateRequest> {

    override fun validate(request: TransactionBatchCreateRequest) {
        if (request.transactions.isNullOrEmpty()) {
            throw BusinessException(
                ValidationFailure(
                    field = "transactions",
                    cause = "Field is null or empty"
                )
            )
        }

        request.transactions.forEach {
            val violations = validator.validate(it)
            if (violations.isNotEmpty()) {
                val bindingResult = BeanPropertyBindingResult(it, "TransactionCreateRequest")
                violations.forEach { violation ->
                    bindingResult.rejectValue(
                        violation.propertyPath.toString(),
                        violation.messageTemplate,
                        arrayOf(violation.invalidValue),
                        violation.message
                    )
                }
                createValidator.validate(bindingResult)
                return
            }
        }
    }
}