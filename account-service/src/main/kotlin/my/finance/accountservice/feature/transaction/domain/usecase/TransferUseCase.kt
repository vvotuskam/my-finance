package my.finance.accountservice.feature.transaction.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.Account
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.transaction.domain.failure.AccountFromNotFoundFailure
import my.finance.accountservice.feature.transaction.domain.failure.AccountToNotFoundFailure
import my.finance.accountservice.feature.transaction.domain.failure.InsufficientFundsFailure
import my.finance.accountservice.feature.transaction.domain.usecase.TransferUseCase.TransferParams
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.nio.DoubleBuffer
import java.util.UUID

@Component
class TransferUseCase(
    private val accountService: AccountService
) : UseCase<TransferParams, SuccessResponse> {

    data class TransferParams(
        val user: User,
        val fromId: UUID,
        val toId: UUID,
        val amount: Double
    )

    @Transactional
    override fun invoke(params: TransferParams): SuccessResponse {
        val (user, fromId, toId, amount) = params

        val from = accountService.findByIdAndUser(fromId, user)
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

        return SuccessResponse(status = "Transferred")
    }
}