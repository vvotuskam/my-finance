package my.finance.accountservice.feature.account.data

import my.finance.accountservice.core.data.entity.User
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

    fun saveAll(accounts: List<Account>): List<Account> {
        return accountRepository.saveAll(accounts)
    }

    fun findByNameAndUser(name: String, user: User): Account? {
        return accountRepository.findByNameIgnoreCaseAndUser(name, user)
    }

    fun findById(id: UUID): Account? {
        return accountRepository.findByIdOrNull(id)
    }

    fun findByIdAndUser(id: UUID, user: User): Account? {
        return accountRepository.findByIdAndUser(id, user)
    }

    fun findAll(): List<Account> {
        return accountRepository.findAll()
    }
}