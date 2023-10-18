package my.finance.accountservice.feature.company.data.company

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CompanyRepository : JpaRepository<Company, UUID> {
}