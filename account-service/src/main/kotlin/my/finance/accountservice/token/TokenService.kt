package my.finance.accountservice.token

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TokenService(
    private val tokenRepository: TokenRepository
) {

    @Transactional
    fun save(token: Token): Token {
        return tokenRepository.save(token)
    }

    fun findByJwt(jwt: String): Token? {
        return tokenRepository.findByJwt(jwt)
    }
}