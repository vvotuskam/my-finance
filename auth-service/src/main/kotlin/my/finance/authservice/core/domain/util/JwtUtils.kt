//package my.finance.authservice.core.domain.util
//
//import io.jsonwebtoken.Jwts
//import io.jsonwebtoken.io.Decoders
//import io.jsonwebtoken.security.Keys
//import my.finance.authservice.core.config.security.JwtConfig
//import org.springframework.stereotype.Component
//import java.security.Key
//import java.time.temporal.ChronoUnit.HOURS
//import java.util.*
//
//
//@Component
//class JwtUtils(
//    private val config: JwtConfig,
//) {
//
//    fun generateJwt(user: User): String {
//        val issuedAt = Date()
//        val expiresAt = Date.from(
//            issuedAt.toInstant().plus(config.expiration, HOURS)
//        )
//
//        return Jwts.builder()
//            .setHeaderParam("typ", "JWT")
//            .setSubject(user.email)
//            .claim("role", user.role)
//            .setIssuedAt(issuedAt)
//            .setExpiration(expiresAt)
//            .signWith(key())
//            .compact()
//    }
//
//    private fun key(): Key {
//        val keyBytes = Decoders.BASE64.decode(config.secret)
//        return Keys.hmacShaKeyFor(keyBytes)
//    }
//}