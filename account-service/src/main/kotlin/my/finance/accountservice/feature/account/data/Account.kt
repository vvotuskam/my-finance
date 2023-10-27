package my.finance.accountservice.feature.account.data

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "accounts")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    val amount: Double,

    val email: String
)