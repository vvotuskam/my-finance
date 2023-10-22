package my.finance.transactionservice.feature.transaction.rest.mapper

import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase.CreateParams
import my.finance.transactionservice.feature.transaction.rest.dto.TransactionCreateRequest
import org.springframework.stereotype.Component

@Component
class TransactionCreateMapper : Mapper<TransactionCreateRequest, CreateParams> {

    override fun convert(request: TransactionCreateRequest): CreateParams {
        TODO("Not yet implemented")
    }
}