package my.finance.authservice.feature.auth.rest.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class AuthResponse(

    @JsonProperty("access_token")
    val accessToken: String
)