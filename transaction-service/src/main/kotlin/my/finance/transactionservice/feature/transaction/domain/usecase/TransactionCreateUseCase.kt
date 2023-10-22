package my.finance.transactionservice.feature.transaction.domain.usecase

import my.finance.transactionservice.core.domain.usecase.UseCase
import my.finance.transactionservice.core.rest.dto.SuccessResponse
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase.CreateParams
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class TransactionCreateUseCase(

) : UseCase<CreateParams, SuccessResponse> {

    data class CreateParams(
        val accountId: UUID,
        val secondId: UUID,
        val amount: Double,
        val isPositive: Boolean
    )

    override fun invoke(params: CreateParams): SuccessResponse {
        TODO("Not yet implemented")
    }
}