package my.finance.transactionservice.feature.transaction.data

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    val transferredAt: LocalDateTime = LocalDateTime.now(),

    val amount: Double,
    val accountId: UUID,
    val secondId: UUID,
    val isPositive: Boolean,
)