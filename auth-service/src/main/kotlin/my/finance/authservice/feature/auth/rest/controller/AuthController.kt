package my.finance.authservice.feature.auth.rest.controller

import jakarta.validation.Valid
import my.finance.authservice.core.config.source.KeycloakDataSourceService
import my.finance.authservice.feature.auth.domain.mapper.AuthMapper
import my.finance.authservice.feature.auth.domain.usecase.AuthUseCase
import my.finance.authservice.feature.auth.domain.validator.AuthValidator
import my.finance.authservice.feature.auth.rest.dto.AuthRequest
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
    private val authUseCase: AuthUseCase,

    private val keycloakDataSourceService: KeycloakDataSourceService
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

    @PostMapping("/create")
    fun create(
        @RequestBody @Valid request: AuthRequest,
        result: BindingResult
    ): ResponseEntity<out Any> {
        keycloakDataSourceService.createUser(email = request.email!!, password = request.password!!)
        return ResponseEntity.ok("Created")
    }
}