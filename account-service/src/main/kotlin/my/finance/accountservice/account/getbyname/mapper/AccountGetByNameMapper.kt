package my.finance.accountservice.account.getbyname.mapper

import my.finance.accountservice.account.getbyname.dto.AccountGetByNameRequest
import my.finance.accountservice.account.getbyname.usecase.AccountGetByNameUseCase.AccountGetByIdParams
import my.finance.accountservice.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class AccountGetByNameMapper : Mapper<AccountGetByNameRequest, AccountGetByIdParams> {

    override fun convert(request: AccountGetByNameRequest): AccountGetByIdParams {
        return AccountGetByIdParams(
            name = request.name!!
        )
    }
}