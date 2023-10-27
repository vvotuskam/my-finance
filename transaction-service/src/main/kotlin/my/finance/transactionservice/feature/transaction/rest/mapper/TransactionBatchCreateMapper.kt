package my.finance.transactionservice.feature.transaction.rest.mapper

import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionBatchCreateUseCase
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionBatchCreateUseCase.BatchCreateParams
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase.*
import my.finance.transactionservice.feature.transaction.rest.dto.request.TransactionBatchCreateRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionBatchCreateMapper : Mapper<TransactionBatchCreateRequest, BatchCreateParams> {
    override fun convert(request: TransactionBatchCreateRequest): BatchCreateParams {
        return BatchCreateParams(
            createParamsList = request.transactions!!.map {
                CreateParams(
                    email = it.email!!,
                    description = it.description!!,
                    accountId = UUID.fromString(it.accountId!!),
                    secondId = UUID.fromString(it.secondId!!),
                    amount = it.amount!!,
                    isPositive = it.isPositive!!
                )
            }
        )
    }
}