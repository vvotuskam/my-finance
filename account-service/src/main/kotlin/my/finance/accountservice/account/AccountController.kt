package my.finance.accountservice.account

import jakarta.validation.Valid
import my.finance.accountservice.account.create.dto.AccountCreateRequest
import my.finance.accountservice.account.create.mapper.AccountCreateMapper
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase
import my.finance.accountservice.validator.AccountCreateValidator
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val createUseCase: AccountCreateUseCase,
    private val createMapper: AccountCreateMapper,
    private val createValidator: AccountCreateValidator,
) {

    @PostMapping("/create")
    fun createAccount(
        @Valid @RequestBody request: AccountCreateRequest,
        result: BindingResult
    ): ResponseEntity<*> {
        createValidator.validate(result)
        val params = createMapper.convert(request)
        return ResponseEntity.ok(createUseCase.invoke(params))
    }
}