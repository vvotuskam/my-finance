package my.finance.accountservice.account.getbyname.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AccountGetByNameRequest(

    @field:NotNull
    @field:NotBlank
    val name: String?
)