package my.finance.accountservice.feature.transaction.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.feature.transaction.rest.dto.response.TransactionResponse
import org.springframework.stereotype.Component

@Component
class HistoryUseCase(

): UseCase<HistoryUseCase.HistoryParams, List<TransactionResponse>> {

    data class HistoryParams(
        val user: User,
        val name: String
    )

    override fun invoke(params: HistoryParams): List<TransactionResponse> {
        TODO("Not yet implemented")
    }
}