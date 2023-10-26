package my.finance.authservice.core.config.source

import com.fasterxml.jackson.databind.ObjectMapper
import feign.FeignException
import feign.FeignException.Conflict
import feign.FeignException.Unauthorized
import my.finance.authservice.core.config.properties.KeycloakProperties
import my.finance.authservice.core.config.source.request.CreateUserRequest
import my.finance.authservice.core.config.source.request.CreateUserRequest.*
import my.finance.authservice.core.config.source.request.KeycloakTokenRequest
import my.finance.authservice.core.config.source.response.KeycloakClientTokenResponse
import my.finance.authservice.core.config.source.response.KeycloakTokenResponse
import my.finance.authservice.core.domain.exception.BusinessException
import my.finance.authservice.core.domain.failure.BadCredentialsFailure
import my.finance.authservice.core.domain.failure.ServiceUnavailableFailure
import my.finance.authservice.feature.registration.domain.failure.UserAlreadyExistsFailure
import org.slf4j.Logger
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class KeycloakDataSourceService(
    private val logger: Logger,
    private val keycloakProperties: KeycloakProperties,
    private val keycloakDataSource: KeycloakDataSource,
) {

    companion object {
        private const val GRANT_TYPE = "password"
        private const val CLIENT_CREDENTIALS = "client_credentials"
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

    fun clientToken(): KeycloakClientTokenResponse {
        try {
            val map = mapOf(
                "grant_type" to CLIENT_CREDENTIALS,
                "client_id" to keycloakProperties.clientId,
                "client_secret" to keycloakProperties.clientSecret,
            )
            val response = keycloakDataSource.clientToken(map)

            return response.body ?: throw BusinessException(ServiceUnavailableFailure())
        } catch (e: Unauthorized) {
            logger.error("Unauthorized feign exception", e)
            throw BusinessException(BadCredentialsFailure())
        } catch (e: FeignException) {
            logger.error("Unexpected feign exception", e)
            throw BusinessException(ServiceUnavailableFailure())
        }
    }

    fun createUser(email: String, password: String) {
        try {
            val accessToken = clientToken().accessToken
            val bearerToken = "Bearer $accessToken"

            val request = CreateUserRequest(email, password)

            keycloakDataSource.createUser(bearerToken, request)
        } catch (e: Conflict) {
            logger.error("Conflict feign exception", e)
            throw BusinessException(UserAlreadyExistsFailure())
        } catch (e: FeignException) {
            logger.error("Unexpected feign exception", e)
            throw BusinessException(ServiceUnavailableFailure())
        }
    }
}