package my.finance.accountservice.account

import jakarta.persistence.*
import my.finance.accountservice.user.User
import java.util.*

@Entity
@Table(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    val amount: Double,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)