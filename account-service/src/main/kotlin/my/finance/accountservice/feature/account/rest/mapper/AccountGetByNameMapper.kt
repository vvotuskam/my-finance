package my.finance.accountservice.feature.account.rest.mapper

import my.finance.accountservice.feature.account.rest.dto.request.AccountGetByNameRequest
import my.finance.accountservice.feature.account.domain.usecase.AccountGetByNameUseCase.AccountGetByIdParams
import my.finance.accountservice.core.rest.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class AccountGetByNameMapper : Mapper<AccountGetByNameRequest, AccountGetByIdParams> {

    override fun convert(request: AccountGetByNameRequest): AccountGetByIdParams {
        return AccountGetByIdParams(
            name = request.name!!
        )
    }
}