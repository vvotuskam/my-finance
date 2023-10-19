package my.finance.accountservice.feature.company.data.company

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
    fun findById(id: UUID): Company? {
        return companyRepository.findByIdOrNull(id)
    }
}