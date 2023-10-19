package my.finance.accountservice.feature.account.domain.usecase

import my.finance.accountservice.core.config.security.SecurityUserDetails
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.account.domain.failure.AccountNotFoundFailure
import my.finance.accountservice.feature.account.domain.usecase.AccountGetByNameUseCase.AccountGetByIdParams
import my.finance.accountservice.feature.account.rest.dto.response.AccountFullResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AccountGetByNameUseCase(
    private val accountService: AccountService
): UseCase<AccountGetByIdParams, AccountFullResponse> {

    data class AccountGetByIdParams(
        val name: String
    )

    override fun invoke(params: AccountGetByIdParams): AccountFullResponse {
        val auth = SecurityContextHolder.getContext().authentication
        val details = auth.principal as SecurityUserDetails
        val user = details.user

        val account = accountService.findByNameAndUser(params.name, user)
            ?: throw BusinessException(AccountNotFoundFailure())

        return AccountFullResponse(
            id = account.id!!,
            name = account.name,
            amount = account.amount
        )
    }
}