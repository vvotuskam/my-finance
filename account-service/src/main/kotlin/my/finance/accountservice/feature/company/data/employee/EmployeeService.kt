package my.finance.accountservice.feature.company.data.employee

import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository
) {
}