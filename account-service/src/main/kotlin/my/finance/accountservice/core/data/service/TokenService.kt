package my.finance.accountservice.core.data.service

import my.finance.accountservice.core.data.entity.Token
import my.finance.accountservice.core.data.repository.TokenRepository
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