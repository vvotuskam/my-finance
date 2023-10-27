package my.finance.transactionservice.feature.salary.rest.controller

import jakarta.validation.Valid
import my.finance.transactionservice.core.rest.dto.SuccessResponse
import my.finance.transactionservice.feature.salary.domain.usecase.AccountSalaryUseCase
import my.finance.transactionservice.feature.salary.domain.usecase.CompanySalaryUseCase
import my.finance.transactionservice.feature.salary.domain.usecase.SalaryTransactionUseCase
import my.finance.transactionservice.feature.salary.domain.validator.AccountSalaryValidator
import my.finance.transactionservice.feature.salary.domain.validator.CompanySalaryValidator
import my.finance.transactionservice.feature.salary.domain.validator.SalaryEmployeesValidator
import my.finance.transactionservice.feature.salary.domain.validator.SalaryTransactionValidator
import my.finance.transactionservice.feature.salary.rest.dto.request.AccountSalaryRequest
import my.finance.transactionservice.feature.salary.rest.dto.request.CompanySalaryRequest
import my.finance.transactionservice.feature.salary.rest.dto.request.SalaryTransactionRequest
import my.finance.transactionservice.feature.salary.rest.dto.response.SalaryTransactionResponse
import my.finance.transactionservice.feature.salary.rest.mapper.AccountSalaryMapper
import my.finance.transactionservice.feature.salary.rest.mapper.CompanySalaryMapper
import my.finance.transactionservice.feature.salary.rest.mapper.SalaryTransactionMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/salary/transaction")
class SalaryController(
    private val salaryValidator: SalaryTransactionValidator,
    private val employeesValidator: SalaryEmployeesValidator,
    private val salaryMapper: SalaryTransactionMapper,
    private val salaryUseCase: SalaryTransactionUseCase,

    private val companySalaryValidator: CompanySalaryValidator,
    private val companySalaryMapper: CompanySalaryMapper,
    private val companySalaryUseCase: CompanySalaryUseCase,

    private val accountSalaryValidator: AccountSalaryValidator,
    private val accountSalaryMapper: AccountSalaryMapper,
    private val accountSalaryUseCase: AccountSalaryUseCase

) {

    @PostMapping
    fun paySalary(
        @RequestBody @Valid request: SalaryTransactionRequest,
        result: BindingResult,
    ): ResponseEntity<SuccessResponse> {
        salaryValidator.validate(result)
        employeesValidator.validate(request)
        val params = salaryMapper.convert(request)
        return ResponseEntity.ok(salaryUseCase(params))
    }

    @GetMapping("/company/{companyId}")
    fun getCompanyTransactions(
        @PathVariable companyId: String
    ): ResponseEntity<SalaryTransactionResponse> {
        val request = CompanySalaryRequest(companyId)
        companySalaryValidator.validate(request)
        val params = companySalaryMapper.convert(request)
        return ResponseEntity.ok(companySalaryUseCase(params))
    }

    @GetMapping("/account/{accountId}")
    fun getAccountTransactions(
        @PathVariable accountId: String
    ): ResponseEntity<SalaryTransactionResponse> {
        val request = AccountSalaryRequest(accountId)
        accountSalaryValidator.validate(request)
        val params = accountSalaryMapper.convert(request)
        return ResponseEntity.ok(accountSalaryUseCase(params))
    }
}