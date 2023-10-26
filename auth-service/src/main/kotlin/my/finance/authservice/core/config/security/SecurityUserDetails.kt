package my.finance.authservice.core.config.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

class SecurityUserDetails(
    email: String,
    password: String,
    authorities: List<SimpleGrantedAuthority>
): User(email, password, authorities)