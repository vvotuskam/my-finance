package my.finance.accountservice.feature.account.rest.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AccountCreateRequest(

    @get:NotNull
    @get:NotBlank
    val name: String?
)