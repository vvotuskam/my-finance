package my.finance.accountservice.feature.company.rest.mapper

import my.finance.accountservice.core.config.security.SecurityUserDetails
import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.company.domain.usecase.SalaryPayUseCase.SalaryPayParams
import my.finance.accountservice.feature.company.rest.dto.request.SalaryPayRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class SalaryPayMapper : Mapper<SalaryPayRequest, SalaryPayParams> {

    override fun convert(request: SalaryPayRequest): SalaryPayParams {
        val securityUser = request.authentication.principal as SecurityUserDetails
        return SalaryPayParams(
            admin = securityUser.user,
            companyId = UUID.fromString(request.companyId),
            employeeId = UUID.fromString(request.employeeId),
        )
    }
}