package my.finance.accountservice.feature.transaction.rest.mapper

import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.transaction.domain.usecase.TransferUseCase
import my.finance.accountservice.feature.transaction.rest.dto.request.TransferRequest
import org.springframework.stereotype.Component

@Component
class TransferMapper : Mapper<TransferRequest, TransferUseCase.TransferParams> {
    override fun convert(request: TransferRequest): TransferUseCase.TransferParams {
        TODO("Not yet implemented")
    }
}