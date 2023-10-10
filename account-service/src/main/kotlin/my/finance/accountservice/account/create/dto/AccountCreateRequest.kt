package my.finance.accountservice.account.create.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AccountCreateRequest(

    @get:NotNull
    @get:NotBlank
    val name: String?
)