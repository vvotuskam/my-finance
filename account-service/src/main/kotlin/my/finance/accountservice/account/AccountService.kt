package my.finance.accountservice.account

import my.finance.accountservice.user.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {

    @Transactional
    fun save(account: Account): Account {
        return accountRepository.save(account)
    }

    fun isUniqueName(name: String, user: User): Boolean {
        return accountRepository.findByNameIgnoreCaseAndUser(name, user) == null
    }

    fun findById(id: UUID): Account? {
        return accountRepository.findByIdOrNull(id)
    }

    fun findAll(): List<Account> {
        return accountRepository.findAll()
    }
}