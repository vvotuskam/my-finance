package my.finance.accountservice.account.getbyid.mapper

import my.finance.accountservice.account.getbyid.dto.AccountGetByIdRequest
import my.finance.accountservice.account.getbyid.usecase.AccountGetByIdUseCase.AccountGetByIdParams
import my.finance.accountservice.mapper.Mapper
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountGetByIdMapper : Mapper<AccountGetByIdRequest, AccountGetByIdParams> {

    override fun convert(request: AccountGetByIdRequest): AccountGetByIdParams {
        return AccountGetByIdParams(
            id = UUID.fromString(request.id!!)
        )
    }
}