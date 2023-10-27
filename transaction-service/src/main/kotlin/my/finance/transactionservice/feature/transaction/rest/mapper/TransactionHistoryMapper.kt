package my.finance.transactionservice.feature.transaction.rest.mapper

import my.finance.transactionservice.core.config.security.SecurityUser
import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionHistoryUseCase
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionHistoryUseCase.HistoryParams
import my.finance.transactionservice.feature.transaction.rest.dto.request.TransactionHistoryRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionHistoryMapper : Mapper<TransactionHistoryRequest, HistoryParams> {

    override fun convert(request: TransactionHistoryRequest): HistoryParams {
        val authToken = SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
        val securityUser = authToken.principal as SecurityUser

        return HistoryParams(
            email = securityUser.username,
            accountId = UUID.fromString(request.accountId!!)
        )
    }
}