package my.finance.accountservice.feature.company.data.employee

import jakarta.persistence.*
import my.finance.accountservice.feature.account.data.Account
import my.finance.accountservice.feature.company.data.company.Company
import java.util.*

@Entity
@Table(name = "employees")
data class Employee(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    val surname: String,

    val salary: Double,

    val email: String,

    @OneToOne
    @JoinColumn(name = "account_id")
    val account: Account,

    @ManyToOne
    @JoinColumn(name = "company_id")
    val company: Company,
)