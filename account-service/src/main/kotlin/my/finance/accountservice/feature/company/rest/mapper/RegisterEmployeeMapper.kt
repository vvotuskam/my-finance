package my.finance.accountservice.feature.company.rest.mapper

import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.company.domain.usecase.RegisterEmployeeUseCase
import my.finance.accountservice.feature.company.domain.usecase.RegisterEmployeeUseCase.RegisterParams
import my.finance.accountservice.feature.company.rest.dto.request.RegisterEmployeeRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class RegisterEmployeeMapper : Mapper<RegisterEmployeeRequest, RegisterParams> {

    override fun convert(request: RegisterEmployeeRequest): RegisterParams {
        return RegisterParams(
            name = request.name!!,
            surname = request.surname!!,
            salary = request.salary!!,
            userId = UUID.fromString(request.userId!!),
            accountId = UUID.fromString(request.accountId!!),
            companyId = UUID.fromString(request.companyId!!)
        )
    }
}