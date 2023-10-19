package my.finance.accountservice.core.data.repository

import my.finance.accountservice.core.data.entity.Token
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, UUID> {
    fun findByJwt(jwt: String): Token?
}