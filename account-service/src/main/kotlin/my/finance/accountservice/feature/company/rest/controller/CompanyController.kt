package my.finance.accountservice.feature.company.rest.controller

import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase
import my.finance.accountservice.feature.company.domain.validator.BatchSalaryPayValidator
import my.finance.accountservice.feature.company.rest.dto.request.BatchSalaryPayRequest
import my.finance.accountservice.feature.company.rest.mapper.BatchSalaryPayMapper
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
    private val batchSalaryPayValidator: BatchSalaryPayValidator
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
}