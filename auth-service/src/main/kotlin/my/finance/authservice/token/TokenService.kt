package my.finance.authservice.token

import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenRepository: TokenRepository
) {

    fun save(token: Token): Token {
        return tokenRepository.save(token)
    }
}