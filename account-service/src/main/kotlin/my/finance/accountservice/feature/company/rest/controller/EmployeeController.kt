package my.finance.accountservice.feature.company.rest.controller

import jakarta.validation.Valid
import my.finance.accountservice.feature.company.domain.usecase.RegisterEmployeeUseCase
import my.finance.accountservice.feature.company.domain.validator.RegisterEmployeeValidator
import my.finance.accountservice.feature.company.rest.dto.request.RegisterEmployeeRequest
import my.finance.accountservice.feature.company.rest.mapper.RegisterEmployeeMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/company/employee")
class EmployeeController(
    private val registerUseCase: RegisterEmployeeUseCase,
    private val registerValidator: RegisterEmployeeValidator,
    private val registerMapper: RegisterEmployeeMapper
) {

    @PostMapping("/register")
    fun register(
        @RequestBody @Valid request: RegisterEmployeeRequest,
        result: BindingResult
    ): ResponseEntity<out Any> {
        registerValidator.validate(result)
        val params = registerMapper.convert(request)
        return ResponseEntity.ok(registerUseCase(params))
    }
}