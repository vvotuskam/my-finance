package my.finance.transactionservice.feature.transaction.rest.controller

import jakarta.validation.Valid
import my.finance.transactionservice.core.rest.dto.SuccessResponse
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionHistoryUseCase
import my.finance.transactionservice.feature.transaction.domain.validator.TransactionCreateValidator
import my.finance.transactionservice.feature.transaction.domain.validator.TransactionHistoryValidator
import my.finance.transactionservice.feature.transaction.rest.dto.request.TransactionCreateRequest
import my.finance.transactionservice.feature.transaction.rest.dto.request.TransactionHistoryRequest
import my.finance.transactionservice.feature.transaction.rest.dto.response.TransactionHistoryResponse
import my.finance.transactionservice.feature.transaction.rest.mapper.TransactionCreateMapper
import my.finance.transactionservice.feature.transaction.rest.mapper.TransactionHistoryMapper
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/transaction")
class TransactionController(
    private val createValidator: TransactionCreateValidator,
    private val createMapper: TransactionCreateMapper,
    private val createUseCase: TransactionCreateUseCase,

    private val historyValidator: TransactionHistoryValidator,
    private val historyMapper: TransactionHistoryMapper,
    private val historyUseCase: TransactionHistoryUseCase
) {

    @GetMapping("/history")
    fun history(
        @RequestBody @Valid request: TransactionHistoryRequest,
        result: BindingResult
    ): ResponseEntity<TransactionHistoryResponse> {
        historyValidator.validate(result)
        val params = historyMapper.convert(request)
        return ResponseEntity.ok(historyUseCase(params))
    }

    @PostMapping("/create")
    fun create(
        @RequestBody @Valid request: TransactionCreateRequest,
        result: BindingResult
    ): ResponseEntity<SuccessResponse> {
        createValidator.validate(result)
        val params = createMapper.convert(request)
        return ResponseEntity.ok(createUseCase(params))
    }
}