package my.finance.accountservice.feature.transaction.data

import jakarta.persistence.*
import my.finance.accountservice.feature.account.data.Account
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    val from: Account,

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    val to: Account,

    val transferredAt: LocalDateTime = LocalDateTime.now(),

    val amount: Double
)