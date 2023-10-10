package my.finance.authservice.auth.usecase

import my.finance.authservice.auth.dto.AuthResponse
import my.finance.authservice.auth.usecase.AuthUseCase.AuthParams
import my.finance.authservice.base.UseCase
import my.finance.authservice.base.failure.BadCredentialsFailure
import my.finance.authservice.base.failure.UserNotFoundFailure
import my.finance.authservice.exception.BusinessException
import my.finance.authservice.token.Token
import my.finance.authservice.token.TokenService
import my.finance.authservice.user.UserService
import my.finance.authservice.util.JwtUtils
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