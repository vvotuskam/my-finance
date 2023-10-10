package my.finance.authservice.feature.registration.rest.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class EmailRequest(

    @field:Email
    @field:NotNull
    @field:NotBlank
    val email: String?
)