package my.finance.accountservice.core.config.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.security.jwt")
data class JwtConfig(
    val secret: String,
    val expiration: Long
)