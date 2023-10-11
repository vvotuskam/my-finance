package my.finance.authservice.feature.registration.domain.usecase

import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.rest.dto.SuccessResponse
import my.finance.authservice.feature.registration.domain.usecase.PasswordUseCase.PasswordParams
import org.springframework.stereotype.Component

@Component
class PasswordUseCase(

): UseCase<PasswordParams, SuccessResponse> {

    data class PasswordParams(
        val email: String,
        val code: String,
        val password: String
    )

    override fun invoke(params: PasswordParams): SuccessResponse {
        TODO("Not yet implemented")
    }
}