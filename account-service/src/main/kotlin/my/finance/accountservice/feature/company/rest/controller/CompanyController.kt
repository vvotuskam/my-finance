package my.finance.accountservice.feature.company.rest.controller

import my.finance.accountservice.feature.company.domain.usecase.BatchSalaryPayUseCase
import my.finance.accountservice.feature.company.rest.mapper.BatchSalaryPayMapper
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/company")
class CompanyController(
    private val batchSalaryPayUseCase: BatchSalaryPayUseCase,
    private val batchSalaryPayMapper: BatchSalaryPayMapper,
) {

    @PostMapping("/pay/salary")
    fun paySalary(authentication: Authentication): ResponseEntity<out Any> {
        val params = batchSalaryPayMapper.convert(authentication)
        return ResponseEntity.ok(batchSalaryPayUseCase(params))
    }
}