package my.finance.accountservice.account

import my.finance.accountservice.account.create.dto.AccountCreateRequest
import my.finance.accountservice.account.create.mapper.AccountCreateMapper
import my.finance.accountservice.account.create.usecase.AccountCreateUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val createUseCase: AccountCreateUseCase,
    private val createMapper: AccountCreateMapper,
) {

    @PostMapping("/create")
    fun createAccount(
        @RequestBody request: AccountCreateRequest
    ): ResponseEntity<*> {
        val params = createMapper.convert(request)
        return ResponseEntity.ok(createUseCase.invoke(params))
    }
}