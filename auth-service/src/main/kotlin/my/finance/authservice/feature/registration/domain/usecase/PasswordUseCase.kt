package my.finance.authservice.feature.registration.domain.usecase

import my.finance.authservice.core.config.source.KeycloakDataSourceService
import my.finance.authservice.core.data.otp.OtpService
import my.finance.authservice.core.data.otp.OtpStatus.CONFIRMED
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.domain.util.OtpUtils
import my.finance.authservice.core.rest.dto.SuccessResponse
import my.finance.authservice.feature.registration.domain.failure.CodeNotFoundFailure
import my.finance.authservice.feature.registration.domain.failure.CodeRevokedFailure
import my.finance.authservice.feature.registration.domain.usecase.PasswordUseCase.PasswordParams
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PasswordUseCase(
    private val otpUtils: OtpUtils,
    private val otpService: OtpService,
    private val keycloakDataSource: KeycloakDataSourceService
) : UseCase<PasswordParams, SuccessResponse> {

    data class PasswordParams(
        val email: String,
        val code: String,
        val password: String,
    )

    @Transactional
    override fun invoke(params: PasswordParams): SuccessResponse {
        val (email, code, password) = params

        val otpCode = otpService.findByCodeAndEmail(code, email)
            ?: throw BusinessException(CodeNotFoundFailure())

        if (otpCode.status != CONFIRMED || otpUtils.isExpired(otpCode.expiresAt))
            throw BusinessException(CodeRevokedFailure())

        otpService.revokeAllOtp(email)

        keycloakDataSource.createUser(email, password)

        return SuccessResponse("Registered")
    }
}