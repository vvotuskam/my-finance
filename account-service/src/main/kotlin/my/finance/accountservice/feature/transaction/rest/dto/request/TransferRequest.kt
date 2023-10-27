package my.finance.accountservice.feature.transaction.rest.dto.request

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.UUID

data class TransferRequest(

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val from: String?,

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val to: String?,

    @field:NotNull
    @field:Min(value = 100)
    val amount: Double?,

    @field:NotNull
    @field:NotBlank
    val description: String?
)