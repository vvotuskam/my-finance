package my.finance.authservice.auth.mapper

import my.finance.authservice.auth.dto.AuthRequest
import my.finance.authservice.auth.usecase.AuthUseCase
import my.finance.authservice.auth.usecase.AuthUseCase.AuthParams
import my.finance.authservice.base.Mapper
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