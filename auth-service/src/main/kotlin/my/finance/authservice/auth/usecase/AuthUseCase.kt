package my.finance.authservice.auth.usecase

import my.finance.authservice.auth.dto.AuthResponse
import my.finance.authservice.auth.usecase.AuthUseCase.AuthParams
import my.finance.authservice.base.UseCase
import org.springframework.stereotype.Component

@Component
class AuthUseCase(

) : UseCase<AuthParams, AuthResponse> {

    data class AuthParams(
        val email: String,
        val password: String
    )

    override fun invoke(params: AuthParams): AuthResponse {
        TODO("Not yet implemented")
    }
}