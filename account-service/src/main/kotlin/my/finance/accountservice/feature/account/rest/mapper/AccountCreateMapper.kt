package my.finance.accountservice.feature.account.rest.mapper

import my.finance.accountservice.feature.account.rest.dto.request.AccountCreateRequest
import my.finance.accountservice.feature.account.domain.usecase.AccountCreateUseCase.AccountCreateParams
import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.core.config.security.SecurityUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

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