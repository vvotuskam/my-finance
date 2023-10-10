package my.finance.accountservice.feature.account.rest.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AccountGetByNameRequest(

    @field:NotNull
    @field:NotBlank
    val name: String?
)