package my.finance.transactionservice.feature.transaction.domain.usecase

import my.finance.transactionservice.core.domain.usecase.UseCase
import my.finance.transactionservice.feature.transaction.data.TransactionService
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionHistoryUseCase.HistoryParams
import my.finance.transactionservice.feature.transaction.rest.dto.response.TransactionHistoryResponse
import my.finance.transactionservice.feature.transaction.rest.dto.response.TransactionHistoryResponse.TransactionResponse
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class TransactionHistoryUseCase(
    private val transactionService: TransactionService
) : UseCase<HistoryParams, TransactionHistoryResponse> {

    data class HistoryParams(
        val email: String,
        val accountId: UUID
    )

    override fun invoke(params: HistoryParams): TransactionHistoryResponse {
        val transactions = transactionService.findByEmailAndAccount(
            email = params.email,
            accountId = params.accountId
        ).map {
            TransactionResponse(
                id = it.id!!,
                accountId = it.accountId,
                secondId = it.secondId,
                amount = it.amount,
                email = it.email,
                isPositive = it.isPositive,
                transferredAt = it.transferredAt,
                description = it.description
            )
        }

        return TransactionHistoryResponse(transactions)
    }
}