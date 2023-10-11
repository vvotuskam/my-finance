package my.finance.authservice.feature.registration.domain.usecase

import my.finance.authservice.core.data.otp.OtpService
import my.finance.authservice.core.data.otp.OtpStatus
import my.finance.authservice.core.data.otp.OtpStatus.CONFIRMED
import my.finance.authservice.core.data.otp.OtpStatus.REVOKED
import my.finance.authservice.core.data.user.Role
import my.finance.authservice.core.data.user.User
import my.finance.authservice.core.data.user.UserService
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.domain.util.OtpUtils
import my.finance.authservice.core.rest.dto.SuccessResponse
import my.finance.authservice.feature.registration.domain.failure.CodeNotFoundFailure
import my.finance.authservice.feature.registration.domain.failure.CodeRevokedFailure
import my.finance.authservice.feature.registration.domain.usecase.PasswordUseCase.PasswordParams
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PasswordUseCase(
    private val otpUtils: OtpUtils,
    private val otpService: OtpService,
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
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

        val user = User(
            email = email,
            password = passwordEncoder.encode(password),
            role = Role.ROLE_USER,
            tokens = emptyList()
        )

        userService.save(user)
        otpService.revokeAllOtp(email)

        return SuccessResponse("Registered")
    }
}