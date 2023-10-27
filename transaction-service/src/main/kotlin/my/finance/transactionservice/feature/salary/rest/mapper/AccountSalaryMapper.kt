package my.finance.transactionservice.feature.salary.rest.mapper

import my.finance.transactionservice.core.config.security.SecurityUser
import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.salary.domain.usecase.AccountSalaryUseCase.AccountSalaryParams
import my.finance.transactionservice.feature.salary.domain.usecase.CompanySalaryUseCase.CompanySalaryParams
import my.finance.transactionservice.feature.salary.rest.dto.request.AccountSalaryRequest
import my.finance.transactionservice.feature.salary.rest.dto.request.CompanySalaryRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountSalaryMapper : Mapper<AccountSalaryRequest, AccountSalaryParams> {

    override fun convert(request: AccountSalaryRequest): AccountSalaryParams {
        val authentication = SecurityContextHolder.getContext().authentication
        val user = authentication.principal as SecurityUser
        return AccountSalaryParams(
            accountId = UUID.fromString(request.accountId),
            email = user.username
        )
    }
}