package my.finance.authservice.feature.registration.rest.controller

import jakarta.validation.Valid
import my.finance.authservice.feature.registration.domain.usecase.CodeUseCase
import my.finance.authservice.feature.registration.domain.usecase.EmailUseCase
import my.finance.authservice.feature.registration.domain.usecase.PasswordUseCase
import my.finance.authservice.feature.registration.domain.validator.CodeValidator
import my.finance.authservice.feature.registration.domain.validator.EmailValidator
import my.finance.authservice.feature.registration.domain.validator.PasswordValidator
import my.finance.authservice.feature.registration.rest.dto.CodeRequest
import my.finance.authservice.feature.registration.rest.dto.EmailRequest
import my.finance.authservice.feature.registration.rest.dto.PasswordRequest
import my.finance.authservice.feature.registration.rest.mapper.CodeMapper
import my.finance.authservice.feature.registration.rest.mapper.EmailMapper
import my.finance.authservice.feature.registration.rest.mapper.PasswordMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/validate")
class RegistrationController(
    private val emailUseCase: EmailUseCase,
    private val emailMapper: EmailMapper,
    private val emailValidator: EmailValidator,

    private val codeUseCase: CodeUseCase,
    private val codeMapper: CodeMapper,
    private val codeValidator: CodeValidator,

    private val passwordUseCase: PasswordUseCase,
    private val passwordMapper: PasswordMapper,
    private val passwordValidator: PasswordValidator,
) {

    @PostMapping("/email")
    fun validateEmail(
        @RequestBody @Valid request: EmailRequest,
        result: BindingResult
    ): ResponseEntity<out Any> {
        emailValidator.validate(result)
        val params = emailMapper.convert(request)
        return ResponseEntity.ok(emailUseCase(params))
    }

    @PostMapping("/code")
    fun validateCode(
        @RequestBody @Valid request: CodeRequest,
        result: BindingResult
    ): ResponseEntity<out Any> {
        emailValidator.validate(result)
        codeValidator.validate(result)
        val params = codeMapper.convert(request)
        return ResponseEntity.ok(codeUseCase(params))
    }

    @PostMapping("/password")
    fun validatePassword(
        @RequestBody @Valid request: PasswordRequest,
        result: BindingResult
    ): ResponseEntity<out Any> {
        emailValidator.validate(result)
        codeValidator.validate(result)
        passwordValidator.validate(result)
        val params = passwordMapper.convert(request)
        return ResponseEntity.ok(passwordUseCase(params))
    }
}