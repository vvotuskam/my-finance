package my.finance.authservice.feature.registration.domain.usecase

import my.finance.authservice.core.domain.usecase.UseCase
import my.finance.authservice.core.rest.dto.SuccessResponse
import my.finance.authservice.feature.registration.domain.usecase.CodeUseCase.CodeParams
import org.springframework.stereotype.Component

@Component
class CodeUseCase(

): UseCase<CodeParams, SuccessResponse> {

    data class CodeParams(
        val email: String,
        val code: String
    )

    override fun invoke(params: CodeParams): SuccessResponse {
        TODO("Not yet implemented")
    }
}