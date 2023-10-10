package my.finance.accountservice.account

import my.finance.accountservice.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByNameIgnoreCase(name: String): Account?
}