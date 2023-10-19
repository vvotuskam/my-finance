package my.finance.accountservice.core.data.entity

import jakarta.persistence.*
import my.finance.accountservice.core.data.enums.Role
import my.finance.accountservice.feature.account.data.Account
import java.util.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val email: String,

    val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @OneToMany(mappedBy = "user")
    val accounts: List<Account>
) {
    override fun toString(): String {
        return "User{id=$id, email=$email, password=$password, role=$role}"
    }
}