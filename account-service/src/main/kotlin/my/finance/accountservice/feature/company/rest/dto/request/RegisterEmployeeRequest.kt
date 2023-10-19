package my.finance.accountservice.feature.company.rest.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.UUID

data class RegisterEmployeeRequest(

    @field:NotNull
    @field:NotBlank
    val name: String?,

    @field:NotNull
    @field:NotBlank
    val surname: String?,

    @field:NotNull
    @field:NotBlank
    val salary: Double?,

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val userId: String?,

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val companyId: String?,

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val accountId: String?
)
