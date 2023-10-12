package my.finance.accountservice.feature.organization.data.company

import org.springframework.stereotype.Service

@Service
class CompanyService(
    private val companyRepository: CompanyRepository
) {
}