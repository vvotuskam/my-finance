package my.finance.accountservice.feature.organization.data.employee

import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {
}