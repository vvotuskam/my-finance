package my.finance.authservice.feature.auth.domain.usecase

import my.finance.authservice.feature.auth.rest.dto.AuthResponse
import my.finance.authservice.feature.auth.domain.usecase.AuthUseCase.AuthParams
import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.domain.failure.BadCredentialsFailure
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.data.token.Token
import my.finance.authservice.core.data.token.TokenService
import my.finance.authservice.core.data.user.UserService
import my.finance.authservice.core.domain.util.JwtUtils
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthUseCase(
    private val userService: UserService,
    private val tokenService: TokenService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtils: JwtUtils
) : UseCase<AuthParams, AuthResponse> {

    data class AuthParams(
        val email: String,
        val password: String
    )

    override fun invoke(params: AuthParams): AuthResponse {
        val user = userService.findByEmail(params.email)
            ?: throw BusinessException(BadCredentialsFailure())

        if (!passwordEncoder.matches(params.password, user.password)) {
            throw BusinessException(BadCredentialsFailure())
        }

        val jwt = jwtUtils.generateJwt(user)

        val token = Token(
            jwt = jwt,
            user = user
        )

        tokenService.save(token)

        return AuthResponse(
            accessToken = jwt
        )
    }
}