package my.finance.accountservice.feature.company.rest.controller

import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase
import my.finance.accountservice.feature.company.domain.usecase.SalaryPayUseCase
import my.finance.accountservice.feature.company.domain.validator.BatchSalaryPayValidator
import my.finance.accountservice.feature.company.domain.validator.SalaryPayValidator
import my.finance.accountservice.feature.company.rest.dto.request.BatchSalaryPayRequest
import my.finance.accountservice.feature.company.rest.dto.request.SalaryPayRequest
import my.finance.accountservice.feature.company.rest.mapper.BatchSalaryPayMapper
import my.finance.accountservice.feature.company.rest.mapper.SalaryPayMapper
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/company")
class CompanyController(
    private val batchSalaryPayUseCase: BatchSalaryPayUseCase,
    private val batchSalaryPayMapper: BatchSalaryPayMapper,
    private val batchSalaryPayValidator: BatchSalaryPayValidator,

    private val salaryPayUseCase: SalaryPayUseCase,
    private val salaryPayMapper: SalaryPayMapper,
    private val salaryPayValidator: SalaryPayValidator,
) {

    @PostMapping("/pay/salary/{companyId}")
    fun paySalary(
        @PathVariable companyId: String,
        authentication: Authentication
    ): ResponseEntity<out Any> {
        val request = BatchSalaryPayRequest(authentication, companyId)
        batchSalaryPayValidator.validate(request)
        val params = batchSalaryPayMapper.convert(request)
        return ResponseEntity.ok(batchSalaryPayUseCase(params))
    }

    @PostMapping("/pay/salary/{companyId}/{employeeId}")
    fun paySalaryToEmployee(
        @PathVariable companyId: String,
        @PathVariable employeeId: String,
        authentication: Authentication
    ): ResponseEntity<out Any> {
        val request = SalaryPayRequest(employeeId, companyId, authentication)
        salaryPayValidator.validate(request)
        val params = salaryPayMapper.convert(request)
        return ResponseEntity.ok(salaryPayUseCase(params))
    }
}