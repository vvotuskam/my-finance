package my.finance.authservice.core.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("keycloak")
class KeycloakProperties(
    val clientId: String,
    val clientSecret: String
)