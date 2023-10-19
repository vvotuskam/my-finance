package my.finance.authservice.core.data.otp

import my.finance.authservice.core.config.otp.OtpProperties
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OtpService(
    private val otpCodeRepository: OtpCodeRepository,
    private val otpProperties: OtpProperties,
) {

    fun save(otp: OtpCode): OtpCode {
        return otpCodeRepository.save(otp)
    }

    fun revokeAllOtp(email: String) {
        otpCodeRepository.revokeOtpByEmail(email)
    }

    fun findByCodeAndEmail(code: String, email: String): OtpCode? {
        return otpCodeRepository.findByCodeAndEmail(code, email)
    }

    fun confirmOtp(otpCode: OtpCode): OtpCode {
        val expiresAt = LocalDateTime.now()
            .plusMinutes(otpProperties.completion)
        val status = OtpStatus.CONFIRMED

        return otpCodeRepository.save(
            otpCode.copy(
                expiresAt = expiresAt,
                status = status
            )
        )
    }
}