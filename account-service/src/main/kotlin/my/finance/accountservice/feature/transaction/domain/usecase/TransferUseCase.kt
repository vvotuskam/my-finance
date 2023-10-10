package my.finance.accountservice.feature.transaction.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.Account
import org.springframework.stereotype.Component
import java.nio.DoubleBuffer

@Component
class TransferUseCase(

) : UseCase<TransferUseCase.TransferParams, SuccessResponse> {

    data class TransferParams(
        val user: User,
        val from: Account,
        val to: Account,
        val amount: Double
    )

    override fun invoke(params: TransferParams): SuccessResponse {
        TODO("Not yet implemented")
    }
}