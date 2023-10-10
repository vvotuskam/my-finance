package my.finance.accountservice.feature.account.data

import my.finance.accountservice.core.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByNameIgnoreCase(name: String): Account?

    fun findByIdAndUser(id: UUID, user: User): Account?
}