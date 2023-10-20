package my.finance.authservice.core.config.source

import feign.FeignException
import feign.FeignException.Unauthorized
import my.finance.authservice.core.config.properties.KeycloakProperties
import my.finance.authservice.core.config.source.request.KeycloakTokenRequest
import my.finance.authservice.core.config.source.response.KeycloakTokenResponse
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.failure.BadCredentialsFailure
import my.finance.authservice.core.domain.failure.ServiceUnavailableFailure
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class KeycloakDataSourceService(
    private val logger: Logger,
    private val keycloakProperties: KeycloakProperties,
    private val keycloakDataSource: KeycloakDataSource,
) {

    companion object {
        private const val GRANT_TYPE = "password"
    }

    fun token(request: KeycloakTokenRequest): KeycloakTokenResponse {
        try {
            val map = mapOf(
                "grant_type" to GRANT_TYPE,
                "client_id" to keycloakProperties.clientId,
                "client_secret" to keycloakProperties.clientSecret,
                "password" to request.password,
                "username" to request.username
            )
            val response = keycloakDataSource.token(map)

            return response.body ?: throw BusinessException(ServiceUnavailableFailure())
        } catch (e: Unauthorized) {
            logger.error("Unauthorized feign exception", e)
            throw BusinessException(BadCredentialsFailure())
        } catch (e: FeignException) {
            logger.error("Unexpected feign exception", e)
            throw BusinessException(ServiceUnavailableFailure())
        }
    }
}