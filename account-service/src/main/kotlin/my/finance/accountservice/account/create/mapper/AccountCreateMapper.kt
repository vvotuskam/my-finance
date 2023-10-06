package my.finance.accountservice.account.create.mapper

import my.finance.accountservice.account.create.dto.AccountCreateRequest
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase.AccountCreateParams
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountCreateMapper {

    fun convert(request: AccountCreateRequest): AccountCreateParams {
        return AccountCreateParams(
            name = request.name!!,
            userId = UUID.fromString(request.userId!!)
        )
    }
}