package my.finance.authservice.core.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("spring.security.oauth2.resourceserver.jwt")
data class JwtDecoderProperties(
    val issuerUri: String
)