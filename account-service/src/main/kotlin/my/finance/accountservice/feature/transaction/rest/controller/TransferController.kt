package my.finance.accountservice.feature.transaction.rest.controller

import jakarta.validation.Valid
import my.finance.accountservice.feature.transaction.domain.usecase.TransferUseCase
import my.finance.accountservice.feature.transaction.domain.validator.TransferValidator
import my.finance.accountservice.feature.transaction.rest.dto.request.TransferRequest
import my.finance.accountservice.feature.transaction.rest.mapper.TransferMapper
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account/transaction")
class TransferController(
    private val transferValidator: TransferValidator,
    private val transferMapper: TransferMapper,
    private val transferUseCase: TransferUseCase,
) {

    @PostMapping("/transfer")
    fun transfer(
        @RequestBody @Valid request: TransferRequest,
        result: BindingResult
    ): ResponseEntity<out Any> {
        transferValidator.validate(result)
        val params = transferMapper.convert(request)
        return ResponseEntity.ok(transferUseCase(params))
    }
}