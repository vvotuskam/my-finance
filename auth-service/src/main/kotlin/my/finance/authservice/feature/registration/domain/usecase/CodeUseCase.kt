package my.finance.authservice.feature.registration.domain.usecase

import my.finance.authservice.core.data.otp.OtpService
import my.finance.authservice.core.data.otp.OtpStatus
import my.finance.authservice.core.data.otp.OtpStatus.REVOKED
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.domain.util.OtpUtils
import my.finance.authservice.core.rest.dto.SuccessResponse
import my.finance.authservice.feature.registration.domain.failure.CodeNotFoundFailure
import my.finance.authservice.feature.registration.domain.failure.CodeRevokedFailure
import my.finance.authservice.feature.registration.domain.usecase.CodeUseCase.CodeParams
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class CodeUseCase(
    private val otpUtils: OtpUtils,
    private val otpService: OtpService,
): UseCase<CodeParams, SuccessResponse> {

    data class CodeParams(
        val email: String,
        val code: String
    )

    @Transactional
    override fun invoke(params: CodeParams): SuccessResponse {
        val (email, code) = params

        val otpCode = otpService.findByCodeAndEmail(code, email)
            ?: throw BusinessException(CodeNotFoundFailure())

        if (otpCode.status == REVOKED || otpUtils.isExpired(otpCode.expiresAt))
            throw BusinessException(CodeRevokedFailure())

        otpService.confirmOtp(otpCode)

        return SuccessResponse("OTP code confirmed")
    }
}