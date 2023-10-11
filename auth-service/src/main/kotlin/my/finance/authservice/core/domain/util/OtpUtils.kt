package my.finance.authservice.core.domain.util

import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.time.LocalDateTime
import kotlin.math.exp

@Component
class OtpUtils {

    fun generateOtp(): String {
        val random = SecureRandom()
        return random.nextInt(10000).toString().padStart(4, '0')
    }

    fun isExpired(expiresAt: LocalDateTime): Boolean {
        return expiresAt.isBefore(LocalDateTime.now())
    }
}