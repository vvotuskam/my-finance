package my.finance.authservice.core.data.otp

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OtpService(
    private val otpCodeRepository: OtpCodeRepository
) {

    fun save(otp: OtpCode): OtpCode {
        return otpCodeRepository.save(otp)
    }

    fun revokeAllOtp(email: String) {
        otpCodeRepository.revokeOtpByEmail(email)
    }
}