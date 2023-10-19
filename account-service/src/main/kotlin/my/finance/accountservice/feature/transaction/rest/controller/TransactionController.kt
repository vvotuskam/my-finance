package my.finance.accountservice.feature.transaction.rest.controller

import my.finance.accountservice.feature.transaction.domain.usecase.HistoryUseCase
import my.finance.accountservice.feature.transaction.rest.dto.request.HistoryRequest
import my.finance.accountservice.feature.transaction.rest.mapper.HistoryMapper
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account/transaction")
class TransactionController(
    private val historyMapper: HistoryMapper,
    private val historyUseCase: HistoryUseCase
) {

    @GetMapping("/history/{name}")
    fun history(
        @PathVariable name: String,
        authentication: Authentication
    ): ResponseEntity<out Any> {
        val request = HistoryRequest(name, authentication)
        val params = historyMapper.convert(request)
        return ResponseEntity.ok(historyUseCase(params))
    }
}