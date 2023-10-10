package my.finance.accountservice.core.data.service

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.data.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findById(id: UUID): User? {
        return userRepository.findByIdOrNull(id)
    }
}