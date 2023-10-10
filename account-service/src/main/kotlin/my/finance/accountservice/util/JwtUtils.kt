package my.finance.accountservice.util

import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import my.finance.accountservice.exception.BusinessException
import my.finance.accountservice.failure.UnauthorizedFailure
import my.finance.accountservice.security.JwtClaims
import my.finance.accountservice.security.JwtConfig
import my.finance.accountservice.user.Role
import my.finance.accountservice.user.enumValueOfOrNull
import org.springframework.stereotype.Component
import java.security.Key


@Component
class JwtUtils(
    private val config: JwtConfig,
) {

    fun isValid(jwt: String): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwt)
                .body
            true
        } catch (e: Exception) {
            false
        }
    }

    fun extractClaims(jwt: String): JwtClaims {
        return JwtClaims(
            email = extractEmail(jwt),
            role = extractRole(jwt)
        )
    }

    private fun extractEmail(jwt: String): String {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwt)
                .body
                .subject
        } catch (e: JwtException) {
            throw BusinessException(UnauthorizedFailure())
        }
    }

    private fun extractRole(jwt: String): Role {
        try {
            val role = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(jwt)
                .body["role"] as String
            return enumValueOfOrNull<Role>(role)
                ?: throw BusinessException(UnauthorizedFailure())
        } catch (e: JwtException) {
            throw BusinessException(UnauthorizedFailure())
        }
    }

    private fun key(): Key {
        val keyBytes = Decoders.BASE64.decode(config.secret)
        return Keys.hmacShaKeyFor(keyBytes)
    }
}