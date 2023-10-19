package my.finance.authservice.feature.registration.rest.mapper

import my.finance.authservice.core.rest.mapper.Mapper
import my.finance.authservice.feature.registration.domain.usecase.CodeUseCase.CodeParams
import my.finance.authservice.feature.registration.rest.dto.CodeRequest
import org.springframework.stereotype.Component

@Component
class CodeMapper : Mapper<CodeRequest, CodeParams> {

    override fun convert(request: CodeRequest): CodeParams {
        return CodeParams(
            email = request.email!!,
            code = request.code!!
        )
    }
}