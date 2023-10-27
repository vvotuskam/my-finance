package my.finance.accountservice.core.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class SecurityUser(
    email: String,
    password: String,
    authorities: Collection<GrantedAuthority>,
    val jwt: String,
) : User(email, password, authorities)