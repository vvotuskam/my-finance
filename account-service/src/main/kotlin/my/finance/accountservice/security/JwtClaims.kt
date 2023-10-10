package my.finance.accountservice.security

import my.finance.accountservice.user.Role

data class JwtClaims(
    val email: String,
    val role: Role
)