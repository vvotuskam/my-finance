package my.finance.accountservice.account.getbyid.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.UUID

data class AccountGetByIdRequest(

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val id: String?
)