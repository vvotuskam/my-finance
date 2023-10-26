package my.finance.authservice.feature.registration.domain.usecase

import my.finance.authservice.core.config.otp.OtpProperties
import my.finance.authservice.core.data.otp.OtpCode
import my.finance.authservice.core.data.otp.OtpService
import my.finance.authservice.core.data.otp.OtpStatus
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.domain.util.OtpUtils
import my.finance.authservice.core.rest.dto.SuccessResponse
import my.finance.authservice.feature.registration.domain.failure.EmailExistsFailure
import my.finance.authservice.feature.registration.domain.usecase.EmailUseCase.EmailParams
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Component
class EmailUseCase(
    private val otpService: OtpService,
    private val otpUtils: OtpUtils,
    private val otpProperties: OtpProperties,
) : UseCase<EmailParams, SuccessResponse> {

    data class EmailParams(
        val email: String,
    )

    @Transactional
    override fun invoke(params: EmailParams): SuccessResponse {

        val expiresAt = LocalDateTime.now()
            .plusMinutes(otpProperties.confirmation)

        val otpCode = OtpCode(
            code = otpUtils.generateOtp(),
            status = OtpStatus.ACTIVE,
            expiresAt = expiresAt,
            email = params.email
        )

        otpService.revokeAllOtp(params.email)
        otpService.save(otpCode)

        return SuccessResponse("OTP code sent")
    }
}