package my.finance.accountservice.feature.transaction.domain.source.service

import feign.FeignException
import feign.FeignException.Unauthorized
import my.finance.accountservice.core.config.security.SecurityUser
import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.ServiceUnavailableFailure
import my.finance.accountservice.core.domain.failure.UnauthorizedFailure
import my.finance.accountservice.feature.transaction.domain.source.client.TransactionClient
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionBatchCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionCreateRequest
import my.finance.accountservice.feature.transaction.domain.source.request.TransactionHistoryRequest
import my.finance.accountservice.feature.transaction.domain.source.response.TransactionHistoryResponse
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionClient: TransactionClient,
    private val logger: Logger
) {

    fun history(
        request: TransactionHistoryRequest,
        securityUser: SecurityUser
    ): TransactionHistoryResponse {
        try {
            val bearerToken = "Bearer ${securityUser.jwt}"
            val response = transactionClient.history(request, bearerToken)
            return response.body ?: throw BusinessException(ServiceUnavailableFailure())
        } catch (e: Unauthorized) {
            logger.error("Unauthorized feign exception", e)
            throw BusinessException(UnauthorizedFailure())
        } catch (e: FeignException) {
            logger.error("Unexpected feign exception", e)
            throw BusinessException(ServiceUnavailableFailure())
        }
    }

    fun create(
        request: TransactionCreateRequest,
        securityUser: SecurityUser
    ) {
        try {
            val bearerToken = "Bearer ${securityUser.jwt}"
            val response = transactionClient.create(request, bearerToken)
            response.body ?: throw BusinessException(ServiceUnavailableFailure())
        } catch (e: Unauthorized) {
            logger.error("Unauthorized feign exception", e)
            throw BusinessException(UnauthorizedFailure())
        } catch (e: FeignException) {
            logger.error("Unexpected feign exception", e)
            throw BusinessException(ServiceUnavailableFailure())
        }
    }

    fun batchCreate(
        request: TransactionBatchCreateRequest,
        securityUser: SecurityUser
    ) {
        try {
            val bearerToken = "Bearer ${securityUser.jwt}"
            val response = transactionClient.batchCreate(request, bearerToken)
            response.body ?: throw BusinessException(ServiceUnavailableFailure())
        } catch (e: Unauthorized) {
            logger.error("Unauthorized feign exception", e)
            throw BusinessException(UnauthorizedFailure())
        } catch (e: FeignException) {
            logger.error("Unexpected feign exception", e)
            throw BusinessException(ServiceUnavailableFailure())
        }
    }
}