package my.finance.accountservice.core.data.repository

import my.finance.accountservice.core.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, UUID> {

    fun findByEmail(email: String): User?
}