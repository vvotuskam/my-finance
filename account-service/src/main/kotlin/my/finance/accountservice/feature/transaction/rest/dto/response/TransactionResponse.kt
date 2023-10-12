package my.finance.accountservice.feature.transaction.rest.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import my.finance.accountservice.feature.account.rest.dto.response.AccountFullResponse
import my.finance.accountservice.feature.account.rest.dto.response.AccountResponse
import java.util.UUID

data class TransactionResponse(

    val id: UUID,

    @JsonProperty("account_from")
    val accountFrom: AccountResponse,

    @JsonProperty("account_to")
    val accountTo: AccountResponse,

    val amount: Double
)