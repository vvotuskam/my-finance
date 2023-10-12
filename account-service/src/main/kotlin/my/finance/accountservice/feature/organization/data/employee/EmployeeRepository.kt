package my.finance.accountservice.feature.organization.data.employee

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface EmployeeRepository : JpaRepository<Employee, UUID> {
}