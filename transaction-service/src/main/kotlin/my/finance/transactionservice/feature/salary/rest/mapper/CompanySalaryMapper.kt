package my.finance.transactionservice.feature.salary.rest.mapper

import my.finance.transactionservice.core.config.security.SecurityUser
import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.salary.domain.usecase.CompanySalaryUseCase
import my.finance.transactionservice.feature.salary.domain.usecase.CompanySalaryUseCase.CompanySalaryParams
import my.finance.transactionservice.feature.salary.rest.dto.request.CompanySalaryRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class CompanySalaryMapper : Mapper<CompanySalaryRequest, CompanySalaryParams> {

    override fun convert(request: CompanySalaryRequest): CompanySalaryParams {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = authentication.principal as SecurityUser
        return CompanySalaryParams(
            companyId = UUID.fromString(request.companyId),
            admin = user.username
        )
    }
}