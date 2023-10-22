package my.finance.transactionservice.feature.transaction.rest.dto

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.UUID
import java.time.temporal.TemporalAmount

class TransactionCreateRequest(

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val accountId: String?,

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val secondId: String?,

    @field:NotNull
    @field:Min(value = 100)
    val amount: Double?,

    @field:NotNull
    val isPositive: Boolean?
)