package my.finance.accountservice.account.create.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AccountCreateRequest(

    @NotNull
    @NotBlank
    val name: String?,

    @NotNull
    val userId: String?
)