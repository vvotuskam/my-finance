package my.finance.accountservice.feature.transaction.domain.usecase

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.transaction.domain.failure.AccountFromNotFoundFailure
import my.finance.accountservice.feature.transaction.domain.failure.AccountToNotFoundFailure
import my.finance.accountservice.feature.transaction.domain.failure.InsufficientFundsFailure
import my.finance.accountservice.feature.transaction.domain.failure.SameAccountsFailure
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.service.TransactionService
import my.finance.accountservice.feature.transaction.domain.usecase.TransferUseCase.TransferParams
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class TransferUseCase(
    private val accountService: AccountService,
    private val transactionService: TransactionService,
) : UseCase<TransferParams, SuccessResponse> {

    data class TransferParams(
        val email: String,
        val fromId: UUID,
        val toId: UUID,
        val amount: Double,
        val description: String
    )

    @Transactional
    override fun invoke(params: TransferParams): SuccessResponse {
        val (email, fromId, toId, amount, description) = params

        if (fromId == toId) throw BusinessException(SameAccountsFailure())

        val from = accountService.findByIdAndEmail(fromId, email)
            ?: throw BusinessException(AccountFromNotFoundFailure())

        val to = accountService.findById(toId)
            ?: throw BusinessException(AccountToNotFoundFailure())

        if (from.amount < amount) throw BusinessException(InsufficientFundsFailure())

        val updatedFrom = from.copy(
            amount = from.amount - amount
        )

        val updatedTo = to.copy(
            amount = to.amount + amount
        )

        accountService.save(updatedFrom)
        accountService.save(updatedTo)

        val authentication = SecurityContextHolder.getContext().authentication
        val securityUser = authentication.principal as SecurityUser

        val transaction = TransactionCreateRequest(
            accountId = fromId,
            secondId = toId,
            amount = amount,
            isPositive = false,
            description = description,
            email = securityUser.username
        )

        transactionService.create(transaction, securityUser)

        return SuccessResponse(status = "Transferred")
    }
}