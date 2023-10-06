package my.finance.accountservice.user

import jakarta.persistence.*
import my.finance.accountservice.account.Account
import java.util.UUID

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val email: String,

    val password: String,

    @OneToMany(mappedBy = "user")
    val accounts: List<Account>
)