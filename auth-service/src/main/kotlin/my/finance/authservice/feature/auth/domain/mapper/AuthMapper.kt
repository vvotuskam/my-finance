package my.finance.authservice.feature.auth.domain.mapper

import my.finance.authservice.feature.auth.rest.dto.AuthRequest
import my.finance.authservice.feature.auth.domain.usecase.AuthUseCase.AuthParams
import my.finance.authservice.core.rest.mapper.Mapper
import org.springframework.stereotype.Component

@Component
class AuthMapper : Mapper<AuthRequest, AuthParams> {

    override fun convert(request: AuthRequest): AuthParams {
        return AuthParams(
            email = request.email!!,
            password = request.password!!
        )
    }
}