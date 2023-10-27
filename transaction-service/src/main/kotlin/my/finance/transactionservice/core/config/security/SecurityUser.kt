package my.finance.transactionservice.core.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class SecurityUser(
    email: String,
    password: String,
    authorities: Collection<GrantedAuthority>
) : User(email, password, authorities)