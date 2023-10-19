package my.finance.accountservice.core.rest.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping
    fun permitAll(): String {
        return "Permit all"
    }

    @GetMapping("/secure")
    @PreAuthorize("isAuthenticated()")
    fun secure(): String {
        return "Secure"
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    fun user(): String {
        return "Auth user"
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun admin(): String {
        return "Auth admin"
    }
}