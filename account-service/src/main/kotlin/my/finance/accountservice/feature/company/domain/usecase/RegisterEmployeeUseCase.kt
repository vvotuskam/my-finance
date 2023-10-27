package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.domain.exception.BusinessException
import my.finance.accountservice.core.domain.failure.UserNotFoundFailure
import my.finance.accountservice.core.domain.usecase.UseCase
import my.finance.accountservice.core.rest.dto.SuccessResponse
import my.finance.accountservice.feature.account.data.AccountService
import my.finance.accountservice.feature.account.domain.failure.AccountNotFoundFailure
import my.finance.accountservice.feature.company.data.company.CompanyService
import my.finance.accountservice.feature.company.data.employee.Employee
import my.finance.accountservice.feature.company.data.employee.EmployeeService
import my.finance.accountservice.feature.company.domain.failure.CompanyNotFoundFailure
import my.finance.accountservice.feature.company.domain.failure.EmployeeExistsFailure
import my.finance.accountservice.feature.company.domain.failure.InvalidSalaryFailure
import my.finance.accountservice.feature.company.domain.usecase.RegisterEmployeeUseCase.RegisterParams
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Component
class RegisterEmployeeUseCase(
    private val accountService: AccountService,
    private val companyService: CompanyService,
    private val employeeService: EmployeeService
): UseCase<RegisterParams, SuccessResponse> {

    data class RegisterParams(
        val admin: String,
        val name: String,
        val surname: String,
        val salary: Double,
        val accountId: UUID,
        val email: String,
        val companyId: UUID
    )

    @Transactional
    override fun invoke(params: RegisterParams): SuccessResponse {
        val (admin, name, surname, salary, accountId, email, companyId) = params

        if (salary < 1) throw BusinessException(InvalidSalaryFailure())

        val company = companyService.findById(companyId)
            ?: throw BusinessException(CompanyNotFoundFailure())

        if (company.admin != admin) throw BusinessException(CompanyNotFoundFailure())

        val account = accountService.findByIdAndEmail(accountId, email)
            ?: throw BusinessException(AccountNotFoundFailure())

        val exists = company
            .employees
            .any { it.account == account && it.email == email }

        if (exists) throw BusinessException(EmployeeExistsFailure())

        val employee = Employee(
            name = name,
            surname = surname,
            salary = salary,
            email = email,
            account = account,
            company = company
        )

        employeeService.save(employee)

        return SuccessResponse("Employee Registered")
    }
}