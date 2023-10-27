package my.finance.accountservice.feature.account.domain.usecase

import my.finance.accountservice.core.config.security.SecurityUser
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
        val securityUser = auth.principal as SecurityUser
        val email = securityUser.username

        val account = accountService.findByNameAndEmail(params.name, email)
            ?: throw BusinessException(AccountNotFoundFailure())

        return AccountFullResponse(
            id = account.id!!,
            name = account.name,
            amount = account.amount
        )
    }
}