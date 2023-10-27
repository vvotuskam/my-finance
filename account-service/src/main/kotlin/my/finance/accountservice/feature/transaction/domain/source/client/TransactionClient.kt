package my.finance.accountservice.feature.transaction.domain.source.client

import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionBatchCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionHistoryRequest
import my.finance.accountservice.feature.transaction.domain.source.response.TransactionHistoryResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(
    name = "transaction-client",
    url = "http://localhost:8082",
)
interface TransactionClient {

    @PostMapping(
        value = ["/api/transaction/history"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun history(
        @RequestBody request: TransactionHistoryRequest,
        @RequestHeader("Authorization") bearerToken: String
    ): ResponseEntity<TransactionHistoryResponse>

    @PostMapping(
        value = ["/api/transaction/create"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun create(
        @RequestBody request: TransactionCreateRequest,
        @RequestHeader("Authorization") bearerToken: String
    ): ResponseEntity<SuccessResponse>

    @PostMapping(
        value = ["/api/transaction/create/batch"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun batchCreate(
        @RequestBody request: TransactionBatchCreateRequest,
        @RequestHeader("Authorization") bearerToken: String
    ): ResponseEntity<SuccessResponse>
}