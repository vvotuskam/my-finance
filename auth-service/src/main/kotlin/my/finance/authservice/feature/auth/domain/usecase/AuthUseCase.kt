package my.finance.authservice.feature.auth.domain.usecase

import my.finance.authservice.core.config.source.KeycloakDataSourceService
import my.finance.authservice.core.config.source.request.KeycloakTokenRequest
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.feature.auth.domain.usecase.AuthUseCase.AuthParams
import my.finance.authservice.feature.auth.rest.dto.AuthResponse
import org.springframework.stereotype.Component

@Component
class AuthUseCase(
    private val keycloakDataSource: KeycloakDataSourceService
) : UseCase<AuthParams, AuthResponse> {

    data class AuthParams(
        val email: String,
        val password: String
    )

    override fun invoke(params: AuthParams): AuthResponse {
        val request = KeycloakTokenRequest(
            password = params.password,
            username = params.email
        )
        val response = keycloakDataSource.token(request)
        return AuthResponse(
            accessToken = response.accessToken
        )
    }
}