package my.finance.accountservice.core.config.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component

@Component
class JwtConverter : Converter<Jwt, AbstractAuthenticationToken> {

    companion object {
        private const val REALM_ACCESS = "realm_access"
        private const val ROLES = "roles"
        private const val EMAIL = "email"
        private const val ROLE_PREFIX = "ROLE_"
    }

    override fun convert(jwt: Jwt): AbstractAuthenticationToken {
        val email = extractEmail(jwt)
        val password = ""
        val authorities = extractAuthorities(jwt)

        val user = SecurityUser(email, password, authorities, jwt.tokenValue)

        return UsernamePasswordAuthenticationToken(user, password, authorities)
    }

    private fun extractEmail(jwt: Jwt): String {
        return jwt.claims[EMAIL] as String
    }

    private fun extractAuthorities(jwt: Jwt): List<GrantedAuthority> {
        val realmAccess = jwt.claims[REALM_ACCESS] as Map<*, *>
        val roles = realmAccess[ROLES] as List<*>
        return roles
            .map { role -> "$ROLE_PREFIX$role" }
            .map { role -> SimpleGrantedAuthority(role) }
            .toList()
    }
}