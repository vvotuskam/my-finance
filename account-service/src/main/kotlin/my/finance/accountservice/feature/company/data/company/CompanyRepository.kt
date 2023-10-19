package my.finance.accountservice.feature.company.data.company

import my.finance.accountservice.core.data.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CompanyRepository : JpaRepository<Company, UUID> {
}