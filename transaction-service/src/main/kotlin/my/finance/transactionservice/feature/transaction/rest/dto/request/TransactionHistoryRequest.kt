package my.finance.transactionservice.feature.transaction.rest.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.UUID

data class TransactionHistoryRequest(

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val accountId: String?
)