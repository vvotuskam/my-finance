package my.finance.transactionservice.feature.transaction.rest.mapper

import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase.CreateParams
import my.finance.transactionservice.feature.transaction.rest.dto.TransactionCreateRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionCreateMapper : Mapper<TransactionCreateRequest, CreateParams> {

    override fun convert(request: TransactionCreateRequest): CreateParams {
        return CreateParams(
            accountId = UUID.fromString(request.accountId),
            secondId = UUID.fromString(request.secondId),
            amount = request.amount!!,
            isPositive = request.isPositive!!
        )
    }
}