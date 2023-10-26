package my.finance.authservice.core.config.source.response

import com.fasterxml.jackson.annotation.JsonProperty

class KeycloakClientTokenResponse(
    @JsonProperty("access_token")
    val accessToken: String,

    @JsonProperty("expires_in")
    val expiresIn: Int,

    @JsonProperty("refresh_expires_in")
    val refreshExpiresIn: Int,

    @JsonProperty("token_type")
    val tokenType: String,

    @JsonProperty("not-before-policy")
    val notBeforePolicy: Int,

    @JsonProperty("scope")
    val scope: String
)