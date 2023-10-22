package my.finance.transactionservice.core.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

data class SecurityUser(
    val email: String,
    val password: String,
    val authorities: Collection<GrantedAuthority>
) : User(email, password, authorities)