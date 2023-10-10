package my.finance.authservice.core.data.otp

import org.springframework.stereotype.Service

@Service
class OtpService(
    private val otpCodeRepository: OtpCodeRepository
) {
}