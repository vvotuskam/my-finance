package my.finance.accountservice

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/secure/{ping}")
    @PreAuthorize("isAuthenticated()")
    fun pingSecure(@PathVariable ping: String): String {
        return ping
    }
}