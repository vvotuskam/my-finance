package my.finance.accountservice.feature.company.rest.mapper

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.company.domain.usecase.RegisterEmployeeUseCase.RegisterParams
import my.finance.accountservice.feature.company.rest.dto.request.RegisterEmployeeRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class RegisterEmployeeMapper : Mapper<RegisterEmployeeRequest, RegisterParams> {

    override fun convert(request: RegisterEmployeeRequest): RegisterParams {
        val auth = SecurityContextHolder.getContext().authentication
        val securityUser = auth.principal as SecurityUser
        return RegisterParams(
            admin = securityUser.username,
            name = request.name!!,
            surname = request.surname!!,
            salary = request.salary!!,
            email = request.email!!,
            accountId = UUID.fromString(request.accountId!!),
            companyId = UUID.fromString(request.companyId!!)
        )
    }
}