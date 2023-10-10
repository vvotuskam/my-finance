package my.finance.authservice.feature.registration.domain.usecase

import my.finance.authservice.core.data.otp.OtpCode
import my.finance.authservice.core.data.user.UserService
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.rest.dto.SuccessResponse
import my.finance.authservice.feature.registration.domain.failure.EmailExistsFailure
import my.finance.authservice.feature.registration.domain.usecase.EmailUseCase.EmailParams
import org.springframework.stereotype.Component

@Component
class EmailUseCase(
    private val userService: UserService
) : UseCase<EmailParams, SuccessResponse> {

    data class EmailParams(
        val email: String
    )

    override fun invoke(params: EmailParams): SuccessResponse {
        val user = userService.findByEmail(params.email)

        if (user != null) throw BusinessException(EmailExistsFailure())

        val otpCode = OtpCode(

        )
        TODO("Not yet implemented")
    }
}