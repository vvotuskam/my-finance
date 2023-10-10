package my.finance.authservice.feature.auth.rest.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AuthRequest(

    @field:Email
    @field:NotNull
    @field:NotBlank
    val email: String?,

    @field:NotNull
    @field:NotBlank
    val password: String?
)