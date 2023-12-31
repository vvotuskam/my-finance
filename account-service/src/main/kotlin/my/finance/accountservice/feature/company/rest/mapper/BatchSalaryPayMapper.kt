package my.finance.accountservice.feature.company.rest.mapper

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase.BatchSalaryParams
import my.finance.accountservice.feature.company.rest.dto.request.BatchSalaryPayRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class BatchSalaryPayMapper : Mapper<BatchSalaryPayRequest, BatchSalaryParams> {

    override fun convert(request: BatchSalaryPayRequest): BatchSalaryParams {
        val securityUser = request.authentication.principal
                as SecurityUser

        return BatchSalaryParams(
            admin = securityUser.username,
            companyId = UUID.fromString(request.companyId)
        )
    }
}