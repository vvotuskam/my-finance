package my.finance.authservice.feature.auth.domain.usecase

import my.finance.authservice.core.config.source.KeycloakDataSource
import my.finance.authservice.core.config.source.KeycloakDataSourceService
import my.finance.authservice.core.config.source.request.KeycloakTokenRequest
import my.finance.authservice.core.data.token.Token
import my.finance.authservice.core.data.token.TokenService
import my.finance.authservice.core.data.user.UserService
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.failure.BadCredentialsFailure
import my.finance.authservice.core.domain.failure.ServiceUnavailableFailure
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.domain.util.JwtUtils
import my.finance.authservice.feature.auth.domain.usecase.AuthUseCase.AuthParams
import my.finance.authservice.feature.auth.rest.dto.AuthResponse
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthUseCase(
    private val userService: UserService,
    private val tokenService: TokenService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtils: JwtUtils,
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