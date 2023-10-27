package my.finance.transactionservice.feature.salary.rest.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.UUID

data class SalaryTransactionRequest(

    @field:UUID
    @field:NotNull
    @field:NotBlank
    val companyId: String?,

    @field:NotNull
    @field:NotEmpty
    val employees: List<Employee>?,
) {
    data class Employee(

        @field:NotNull
        @field:Min(value = 1)
        val salary: Double?,

        @field:UUID
        @field:NotNull
        @field:NotBlank
        val accountId: String?,

        @field:Email
        @field:NotNull
        @field:NotBlank
        val email: String?
    )
}