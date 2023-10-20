package my.finance.authservice.core.config.source.request

import feign.form.FormProperty

data class KeycloakTokenRequest(
    val password: String,
    val username: String,
)