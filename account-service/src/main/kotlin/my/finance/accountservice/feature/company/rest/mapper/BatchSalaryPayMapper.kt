package my.finance.accountservice.feature.company.rest.mapper

import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase
import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase.BatchSalaryParams
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class BatchSalaryPayMapper : Mapper<Authentication, BatchSalaryParams> {

    override fun convert(request: Authentication): BatchSalaryParams {
        TODO("Not yet implemented")
    }
}