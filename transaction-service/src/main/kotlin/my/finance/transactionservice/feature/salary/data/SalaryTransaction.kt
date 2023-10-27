package my.finance.transactionservice.feature.salary.data

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "salary_transactions")
data class SalaryTransaction(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    val transferredAt: LocalDateTime = LocalDateTime.now(),

    val email: String,
    val amount: Double,
    val accountId: UUID,
    val companyId: UUID,
    val admin: String
)