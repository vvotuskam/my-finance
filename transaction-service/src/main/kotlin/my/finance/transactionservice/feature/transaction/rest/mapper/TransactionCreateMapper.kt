package my.finance.transactionservice.feature.transaction.rest.mapper

import my.finance.transactionservice.core.config.security.SecurityUser
import my.finance.transactionservice.core.rest.mapper.Mapper
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase.CreateParams
import my.finance.transactionservice.feature.transaction.rest.dto.request.TransactionCreateRequest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.util.*

@Component
class TransactionCreateMapper : Mapper<TransactionCreateRequest, CreateParams> {

    override fun convert(request: TransactionCreateRequest): CreateParams {
        val authToken = SecurityContextHolder.getContext().authentication as UsernamePasswordAuthenticationToken
        val securityUser = authToken.principal as SecurityUser
        return CreateParams(
            accountId = UUID.fromString(request.accountId),
            secondId = UUID.fromString(request.secondId),
            amount = request.amount!!,
            isPositive = request.isPositive!!,
            email = securityUser.email,
            description = request.description!!
        )
    }
}