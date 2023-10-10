package my.finance.accountservice.account.create.mapper

import my.finance.accountservice.account.create.dto.AccountCreateRequest
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase.AccountCreateParams
import my.finance.accountservice.mapper.Mapper
import my.finance.accountservice.security.SecurityUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountCreateMapper : Mapper<AccountCreateRequest, AccountCreateParams> {

    override fun convert(request: AccountCreateRequest): AccountCreateParams {

        val auth = SecurityContextHolder.getContext().authentication
        val userDetails = auth.principal as SecurityUserDetails

        return AccountCreateParams(
            name = request.name!!,
            user = userDetails.user
        )
    }
}