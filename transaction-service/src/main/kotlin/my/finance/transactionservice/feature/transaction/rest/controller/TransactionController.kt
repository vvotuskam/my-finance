package my.finance.transactionservice.feature.transaction.rest.controller

import jakarta.validation.Valid
import my.finance.transactionservice.core.rest.dto.SuccessResponse
import my.finance.transactionservice.feature.transaction.domain.usecase.TransactionCreateUseCase
import my.finance.transactionservice.feature.transaction.domain.validator.TransactionCreateValidator
import my.finance.transactionservice.feature.transaction.rest.dto.TransactionCreateRequest
import my.finance.transactionservice.feature.transaction.rest.mapper.TransactionCreateMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
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
) {

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