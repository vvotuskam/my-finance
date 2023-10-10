package my.finance.accountservice.core.config.security

import my.finance.accountservice.core.data.enums.Role

data class JwtClaims(
    val email: String,
    val role: Role
)