package my.finance.accountservice.feature.company.data.company

import jakarta.persistence.*
import my.finance.accountservice.feature.company.data.employee.Employee
import java.util.*

@Entity
@Table(name = "companies")
data class Company(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    val admin: String,

    @OneToMany(mappedBy = "company")
    val employees: List<Employee>
) {

    override fun toString(): String {
        return "Company{id=$id, name=$name}"
    }
}