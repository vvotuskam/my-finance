package my.finance.transactionservice.feature.transaction.domain.usecase

import my.finance.transactionservice.core.domain.usecase.UseCase
import my.finance.transactionservice.core.rest.dto.SuccessResponse
import my.finance.transactionservice.feature.transaction.data.Transaction
import my.finance.transactionservice.feature.transaction.data.TransactionService
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase.CreateParams
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class TransactionCreateUseCase(
    private val transactionService: TransactionService
) : UseCase<CreateParams, SuccessResponse> {

    data class CreateParams(
        val email: String,
        val accountId: UUID,
        val secondId: UUID,
        val amount: Double,
        val isPositive: Boolean
    )

    @Transactional
    override fun invoke(params: CreateParams): SuccessResponse {
        val transaction = Transaction(
            accountId = params.accountId,
            secondId = params.secondId,
            amount = params.amount,
            isPositive = params.isPositive,
            email = params.email
        )
        transactionService.save(transaction)
        return SuccessResponse("Transaction created")
    }
}