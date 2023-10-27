package my.finance.accountservice.feature.transaction.rest.mapper

import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.transaction.domain.usecase.TransferUseCase.TransferParams
import my.finance.accountservice.feature.transaction.rest.dto.request.TransferRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransferMapper : Mapper<TransferRequest, TransferParams> {

    override fun convert(request: TransferRequest): TransferParams {
        val auth = SecurityContextHolder.getContext().authentication
        val securityUser = auth.principal as SecurityUser

        return TransferParams(
            email = securityUser.username,
            fromId = UUID.fromString(request.from!!),
            toId = UUID.fromString(request.to!!),
            amount = request.amount!!,
            description = request.description!!
        )
    }
}