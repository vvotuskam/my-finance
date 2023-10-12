package my.finance.accountservice.feature.transaction.rest.mapper

import my.finance.accountservice.core.config.security.SecurityUserDetails
import my.finance.accountservice.core.rest.mapper.Mapper
import my.finance.accountservice.feature.transaction.domain.usecase.HistoryUseCase.HistoryParams
import my.finance.accountservice.feature.transaction.rest.dto.request.HistoryRequest
import org.springframework.stereotype.Component

@Component
class HistoryMapper : Mapper<HistoryRequest, HistoryParams> {

    override fun convert(request: HistoryRequest): HistoryParams {
        val userDetails = request.authentication.principal as SecurityUserDetails
        return HistoryParams(
            user = userDetails.user,
            name = request.name
        )
    }
}