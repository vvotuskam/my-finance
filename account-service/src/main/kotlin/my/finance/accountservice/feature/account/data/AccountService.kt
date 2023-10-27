package my.finance.accountservice.feature.account.data

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

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

    fun findByNameAndEmail(name: String, email: String): Account? {
        return accountRepository.findByNameIgnoreCaseAndEmail(name, email)
    }

    fun findById(id: UUID): Account? {
        return accountRepository.findByIdOrNull(id)
    }

    fun findByIdAndEmail(id: UUID, email: String): Account? {
        return accountRepository.findByIdAndEmail(id, email)
    }

    fun findAll(): List<Account> {
        return accountRepository.findAll()
    }
}