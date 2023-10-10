package my.finance.authservice.core.data.user

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }
}