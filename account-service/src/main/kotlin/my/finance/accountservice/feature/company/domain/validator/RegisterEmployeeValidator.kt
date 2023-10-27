package my.finance.accountservice.feature.company.domain.validator

import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.ValidationFailure
import my.finance.accountservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class RegisterEmployeeValidator : Validator {

    companion object {
        private const val NAME = "name"
        private const val SURNAME = "surname"
        private const val SALARY = "salary"
        private const val USER_ID = "userId"
        private const val ACCOUNT_ID = "accountId"
        private const val COMPANY_ID = "companyId"
        private const val EMAIL = "email"

        private const val DEFAULT_MESSAGE = "Field is null or empty"
        private const val UUID_MESSAGE = "Field is null, empty, or not UUID"
        private const val EMAIL_MESSAGE = "Field is null, empty, or not email"
    }

    override fun validate(result: BindingResult) {

        val failure =  when {
            result.hasFieldErrors(NAME) -> ValidationFailure(NAME, DEFAULT_MESSAGE)
            result.hasFieldErrors(SURNAME) -> ValidationFailure(SURNAME, DEFAULT_MESSAGE)

            result.hasFieldErrors(SALARY) -> ValidationFailure(SALARY, DEFAULT_MESSAGE)

            result.hasFieldErrors(USER_ID) -> ValidationFailure(USER_ID, UUID_MESSAGE)
            result.hasFieldErrors(ACCOUNT_ID) -> ValidationFailure(ACCOUNT_ID, UUID_MESSAGE)
            result.hasFieldErrors(COMPANY_ID) -> ValidationFailure(COMPANY_ID, UUID_MESSAGE)

            result.hasFieldErrors(EMAIL) -> ValidationFailure(EMAIL, EMAIL_MESSAGE)

            else -> null
        }

        if (failure != null) throw BusinessException(failure)
    }
}