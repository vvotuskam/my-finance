package my.finance.authservice.core.config.source

import feign.Headers
import feign.form.ContentType
import my.finance.authservice.core.config.source.config.CustomConfig
import my.finance.authservice.core.config.source.request.CreateUserRequest
import my.finance.authservice.core.config.source.request.KeycloakTokenRequest
import my.finance.authservice.core.config.source.response.KeycloakClientTokenResponse
import my.finance.authservice.core.config.source.response.KeycloakTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart

@FeignClient(
    name = "keycloak",
    url = "http://localhost:8762",
    configuration = [CustomConfig::class]
)
interface KeycloakDataSource {

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/realms/my-finance/protocol/openid-connect/token"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun token(
        @RequestBody request: Map<String, Any>
    ): ResponseEntity<KeycloakTokenResponse>

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/realms/my-finance/protocol/openid-connect/token"],
        consumes = [MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun clientToken(
        @RequestBody request: Map<String, Any>
    ): ResponseEntity<KeycloakClientTokenResponse>

    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/admin/realms/my-finance/users"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun createUser(
        @RequestHeader("Authorization") bearerToken: String,
        @RequestBody request: CreateUserRequest
    ): ResponseEntity<Unit>

}