package my.finance.authservice.core.config.otp

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.security.otp")
data class OtpProperties(
    val confirmation: Long,
    val completion: Long
)