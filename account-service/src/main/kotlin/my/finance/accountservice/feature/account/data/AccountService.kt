package my.finance.accountservice.feature.account.data

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {

    @Transactional
    fun save(account: Account): Account {
        return accountRepository.save(account)
    }

    fun findByName(name: String): Account? {
        return accountRepository.findByNameIgnoreCase(name)
    }

    fun findAll(): List<Account> {
        return accountRepository.findAll()
    }
}