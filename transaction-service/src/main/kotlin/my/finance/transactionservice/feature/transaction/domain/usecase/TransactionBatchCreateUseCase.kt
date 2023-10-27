package my.finance.transactionservice.feature.transaction.domain.usecase

import my.finance.transactionservice.core.domain.usecase.UseCase
import my.finance.transactionservice.core.rest.dto.SuccessResponse
import my.finance.transactionservice.feature.transaction.data.Transaction
import my.finance.transactionservice.feature.transaction.data.TransactionService
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionBatchCreateUseCase.BatchCreateParams
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase.CreateParams
import org.springframework.stereotype.Component

@Component
class TransactionBatchCreateUseCase(
    private val transactionService: TransactionService
): UseCase<BatchCreateParams, SuccessResponse> {

    data class BatchCreateParams(
        val createParamsList: List<CreateParams>
    )

    override fun invoke(params: BatchCreateParams): SuccessResponse {
        val transactions = params.createParamsList.map {
            Transaction(
                accountId = it.accountId,
                secondId = it.secondId,
                amount = it.amount,
                isPositive = it.isPositive,
                email = it.email,
                description = it.description
            )
        }
        transactionService.saveAll(transactions)
        return SuccessResponse("Created")
    }
}