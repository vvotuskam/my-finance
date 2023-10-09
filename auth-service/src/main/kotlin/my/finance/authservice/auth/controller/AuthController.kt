package my.finance.authservice.auth.controller

import jakarta.validation.Valid
import my.finance.authservice.auth.dto.AuthRequest
import my.finance.authservice.auth.mapper.AuthMapper
import my.finance.authservice.auth.usecase.AuthUseCase
import my.finance.authservice.auth.validator.AuthValidator
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authValidator: AuthValidator,
    private val authMapper: AuthMapper,
    private val authUseCase: AuthUseCase
) {

    @PostMapping
    fun authorize(
        @RequestBody @Valid request: AuthRequest,
        result: BindingResult
    ): ResponseEntity<out Any> {
        authValidator.validate(result)
        val params = authMapper.convert(request)
        return ResponseEntity.ok(authUseCase(params))
    }
}