package my.finance.authservice.feature.registration.rest.mapper

import my.finance.authservice.core.rest.mapper.Mapper
import my.finance.authservice.feature.registration.domain.usecase.EmailUseCase
import my.finance.authservice.feature.registration.domain.usecase.EmailUseCase.EmailParams
import my.finance.authservice.feature.registration.rest.dto.EmailRequest
import org.springframework.stereotype.Component

@Component
class EmailMapper : Mapper<EmailRequest, EmailParams> {

    override fun convert(request: EmailRequest): EmailParams {
        return EmailParams(email = request.email!!)
    }
}