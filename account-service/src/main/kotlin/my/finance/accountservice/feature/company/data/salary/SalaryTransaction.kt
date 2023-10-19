package my.finance.accountservice.feature.company.data.salary

import jakarta.persistence.*
import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.feature.company.data.company.Company
import my.finance.accountservice.feature.company.data.employee.Employee
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "salary_transactions")
class SalaryTransaction(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val amount: Double,

    @ManyToOne
    @JoinColumn(name = "company_id")
    val company: Company,

    @ManyToOne
    @JoinColumn(name = "employee_id")
    val employee: Employee,

    @ManyToOne
    @JoinColumn(name = "transferred_by")
    val transferredBy: User,

    val transferredAt: LocalDateTime = LocalDateTime.now()
)