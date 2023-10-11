package my.finance.authservice.feature.registration.rest.mapper

import my.finance.authservice.core.rest.mapper.Mapper
import my.finance.authservice.feature.registration.domain.usecase.PasswordUseCase
import my.finance.authservice.feature.registration.domain.usecase.PasswordUseCase.PasswordParams
import my.finance.authservice.feature.registration.rest.dto.PasswordRequest
import org.springframework.stereotype.Component

@Component
class PasswordMapper : Mapper<PasswordRequest, PasswordParams> {

    override fun convert(request: PasswordRequest): PasswordParams {
        return PasswordParams(
            email = request.email!!,
            code = request.code!!,
            password = request.password!!
        )
    }
}