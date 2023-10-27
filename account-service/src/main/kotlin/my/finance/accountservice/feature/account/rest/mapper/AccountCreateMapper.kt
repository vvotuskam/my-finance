package my.finance.accountservice.feature.account.rest.mapper

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.account.domain.usecase.AccountCreateUseCase.AccountCreateParams
import my.finance.accountservice.feature.account.rest.dto.request.AccountCreateRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AccountCreateMapper : Mapper<AccountCreateRequest, AccountCreateParams> {

    override fun convert(request: AccountCreateRequest): AccountCreateParams {

        val auth = SecurityContextHolder.getContext().authentication
        val user = auth.principal as SecurityUser

        return AccountCreateParams(
            name = request.name!!,
            email = user.username
        )
    }
}