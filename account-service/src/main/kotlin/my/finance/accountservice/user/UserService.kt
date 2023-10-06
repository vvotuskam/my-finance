package my.finance.accountservice.user

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