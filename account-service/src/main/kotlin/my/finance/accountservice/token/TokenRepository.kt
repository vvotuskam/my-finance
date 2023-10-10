package my.finance.accountservice.token

import my.finance.accountservice.token.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TokenRepository : JpaRepository<Token, UUID> {
    fun findByJwt(jwt: String): Token?
}