package my.finance.authservice.feature.registration.rest.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class CodeRequest(

    @field:Email
    @field:NotNull
    @field:NotBlank
    val email: String?,

    @field:NotNull
    @field:NotBlank
    @field:Size(min = 4, max = 4)
    @field:Pattern(regexp = "\\d{4}")
    val code: String?
)
