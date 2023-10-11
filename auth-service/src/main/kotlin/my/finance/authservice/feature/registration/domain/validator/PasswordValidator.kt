package my.finance.authservice.feature.registration.domain.validator

import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.failure.ValidationFailure
import my.finance.authservice.core.domain.validator.Validator
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult

@Component
class PasswordValidator : Validator {

    companion object {
        private const val PASSWORD = "password"
        private const val PASSWORD_MESSAGE = "Field is null, empty, or invalid. " +
                "Password must be of length at least 8, " +
                "contain at least 1 uppercase, 1 lowercase, " +
                "and 1 digit character and must contain only alpha-numeric characters"
    }

    override fun validate(result: BindingResult) {
        val password = result.getFieldValue(PASSWORD) as String

        if (result.hasFieldErrors(PASSWORD) || !isValid(password))
            throw BusinessException(
                failure = ValidationFailure(
                    field = PASSWORD,
                    cause = PASSWORD_MESSAGE
                )
            )
    }

    private fun isValid(password: String): Boolean {
        val chars = password.chars()

        val lowercase = chars.anyMatch { it.toChar().isLowerCase() }
        val uppercase = chars.anyMatch { it.toChar().isUpperCase() }
        val digit = chars.anyMatch { it.toChar().isDigit() }
        val allowedChars = chars.allMatch { it.toChar().isLetterOrDigit() }

        return lowercase && uppercase && digit && allowedChars
    }
}