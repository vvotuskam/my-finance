package my.finance.transactionservice.feature.salary.rest.mapper

import my.finance.transactionservice.core.config.security.SecurityUser
import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.salary.domain.usecase.SalaryTransactionUseCase.SalaryParams
import my.finance.transactionservice.feature.salary.domain.usecase.SalaryTransactionUseCase.SalaryParams.EmployeeParams
import my.finance.transactionservice.feature.salary.rest.dto.request.SalaryTransactionRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class SalaryTransactionMapper : Mapper<SalaryTransactionRequest, SalaryParams> {

    override fun convert(request: SalaryTransactionRequest): SalaryParams {
        val authentication = SecurityContextHolder.getContext().authentication
        val securityUser = authentication.principal as SecurityUser
        return SalaryParams(
            admin = securityUser.username,
            companyId = UUID.fromString(request.companyId!!),
            employees = request.employees!!.map {
                EmployeeParams(
                    accountId = UUID.fromString(it.accountId!!),
                    salary = it.salary!!,
                    email = it.email!!
                )
            }
        )
    }

}