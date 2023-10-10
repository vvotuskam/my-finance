package my.finance.accountservice.feature.account.rest.controller

import jakarta.validation.Valid
import my.finance.accountservice.feature.account.rest.dto.request.AccountCreateRequest
import my.finance.accountservice.feature.account.rest.mapper.AccountCreateMapper
import my.finance.accountservice.feature.account.domain.usecase.AccountCreateUseCase
import my.finance.accountservice.feature.account.domain.validator.AccountCreateValidator
import my.finance.accountservice.feature.account.domain.usecase.AccountGetUseCase
import my.finance.accountservice.feature.account.rest.dto.request.AccountGetByNameRequest
import my.finance.accountservice.feature.account.rest.mapper.AccountGetByNameMapper
import my.finance.accountservice.feature.account.domain.usecase.AccountGetByNameUseCase
import my.finance.accountservice.feature.account.domain.validator.AccountGetByNameValidator
import my.finance.accountservice.core.domain.noparams.NoParams
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val createUseCase: AccountCreateUseCase,
    private val createMapper: AccountCreateMapper,
    private val createValidator: AccountCreateValidator,

    private val getByNameUseCase: AccountGetByNameUseCase,
    private val getByNameMapper: AccountGetByNameMapper,
    private val getByNameValidator: AccountGetByNameValidator,

    private val getUseCase: AccountGetUseCase,
) {

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun getAccount(): ResponseEntity<out Any> {
        return ResponseEntity.ok(getUseCase.invoke(NoParams))
    }

    @GetMapping("/info")
    @PreAuthorize("isAuthenticated()")
    fun getAccount(
        @RequestBody @Valid request: AccountGetByNameRequest,
        result: BindingResult,
    ): ResponseEntity<out Any> {
        getByNameValidator.validate(result)
        val params = getByNameMapper.convert(request)
        return ResponseEntity.ok(getByNameUseCase.invoke(params))
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    fun createAccount(
        @Valid @RequestBody request: AccountCreateRequest,
        result: BindingResult,
    ): ResponseEntity<*> {
        createValidator.validate(result)
        val params = createMapper.convert(request)
        return ResponseEntity.ok(createUseCase.invoke(params))
    }
}