package my.finance.accountservice.account

import jakarta.validation.Valid
import my.finance.accountservice.account.create.dto.AccountCreateRequest
import my.finance.accountservice.account.create.mapper.AccountCreateMapper
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase
import my.finance.accountservice.account.create.validator.AccountCreateValidator
import my.finance.accountservice.account.get.AccountGetUseCase
import my.finance.accountservice.account.getbyid.dto.AccountGetByIdRequest
import my.finance.accountservice.account.getbyid.mapper.AccountGetByIdMapper
import my.finance.accountservice.account.getbyid.usecase.AccountGetByIdUseCase
import my.finance.accountservice.account.getbyid.validator.AccountGetByIdValidator
import my.finance.accountservice.usecase.NoParams
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val createUseCase: AccountCreateUseCase,
    private val createMapper: AccountCreateMapper,
    private val createValidator: AccountCreateValidator,

    private val getByIdUseCase: AccountGetByIdUseCase,
    private val getByIdMapper: AccountGetByIdMapper,
    private val getByIdValidator: AccountGetByIdValidator,

    private val getUseCase: AccountGetUseCase,
) {

    @GetMapping
    fun getAccount(): ResponseEntity<out Any> {
        return ResponseEntity.ok(getUseCase.invoke(NoParams))
    }

    @GetMapping("/info")
    fun getAccount(
        @RequestBody @Valid request: AccountGetByIdRequest,
        result: BindingResult,
    ): ResponseEntity<out Any> {
        getByIdValidator.validate(result)
        val params = getByIdMapper.convert(request)
        return ResponseEntity.ok(getByIdUseCase.invoke(params))
    }

    @PostMapping("/create")
    fun createAccount(
        @Valid @RequestBody request: AccountCreateRequest,
        result: BindingResult,
    ): ResponseEntity<*> {
        createValidator.validate(result)
        val params = createMapper.convert(request)
        return ResponseEntity.ok(createUseCase.invoke(params))
    }
}