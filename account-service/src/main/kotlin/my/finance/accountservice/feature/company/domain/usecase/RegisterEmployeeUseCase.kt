package my.finance.accountservice.feature.company.domain.usecase

import my.finance.accountservice.core.data.entity.User
import my.finance.accountservice.core.data.service.UserService
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
import my.finance.accountservice.feature.company.domain.usecase.RegisterEmployeeUseCase.*
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class RegisterEmployeeUseCase(
    private val accountService: AccountService,
    private val userService: UserService,
    private val companyService: CompanyService,
    private val employeeService: EmployeeService
): UseCase<RegisterParams, SuccessResponse> {

    data class RegisterParams(
        val admin: User,
        val name: String,
        val surname: String,
        val salary: Double,
        val accountId: UUID,
        val userId: UUID,
        val companyId: UUID
    )

    @Transactional
    override fun invoke(params: RegisterParams): SuccessResponse {
        val (admin, name, surname, salary, accountId, userId, companyId) = params

        if (salary < 1) throw BusinessException(InvalidSalaryFailure())

        val user = userService.findById(userId)
            ?: throw BusinessException(UserNotFoundFailure())

        val company = companyService.findById(companyId)
            ?: throw BusinessException(CompanyNotFoundFailure())

        val account = accountService.findById(accountId)
            ?: throw BusinessException(AccountNotFoundFailure())

        if (company.admin.id != admin.id) throw BusinessException(CompanyNotFoundFailure())

        if (account.user != user) throw BusinessException(AccountNotFoundFailure())

        val exists = company
            .employees
            .any { it.account == account && it.user == user }

        if (exists) throw BusinessException(EmployeeExistsFailure())

        val employee = Employee(
            name = name,
            surname = surname,
            salary = salary,
            user = user,
            account = account,
            company = company
        )

        employeeService.save(employee)

        return SuccessResponse("Employee Registered")
    }
}