package my.finance.accountservice.feature.account.data

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByNameIgnoreCaseAndEmail(name: String, email: String): Account?

    fun findByIdAndEmail(id: UUID, email: String): Account?
}