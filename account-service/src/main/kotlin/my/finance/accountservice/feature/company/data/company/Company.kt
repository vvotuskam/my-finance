package my.finance.accountservice.feature.company.data.company

import jakarta.persistence.*
import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.feature.company.data.employee.Employee
import org.hibernate.annotations.Fetch
import java.util.*

@Entity
@Table(name = "companies")
data class Company(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    @OneToOne(fetch = FetchType.EAGER)
    val admin: User,

    @OneToMany(mappedBy = "company")
    val employees: List<Employee>
) {

    override fun toString(): String {
        return "Company{id=$id, name=$name}"
    }
}