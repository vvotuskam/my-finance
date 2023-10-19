package my.finance.accountservice.feature.company.data.employee

import jakarta.persistence.*
import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.feature.account.data.Account
import my.finance.accountservice.feature.company.data.company.Company
import java.util.UUID

@Entity
@Table(name = "employees")
data class Employee(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    val surname: String,

    val salary: Double,

    @OneToOne
    @JoinColumn(name = "account_id")
    val account: Account,

    @OneToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "company_id")
    val company: Company,
)