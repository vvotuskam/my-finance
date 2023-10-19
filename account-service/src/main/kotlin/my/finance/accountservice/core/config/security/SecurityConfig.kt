package my.finance.accountservice.core.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import my.finance.accountservice.core.domain.failure.AccessDeniedFailure
import my.finance.accountservice.core.domain.failure.UnauthorizedFailure
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .exceptionHandling {
                it.authenticationEntryPoint { _, response, _ ->
                    val body = objectMapper.writeValueAsString(UnauthorizedFailure())
                    response.status = HttpStatus.UNAUTHORIZED.value()
                    response.writer.write(body)
                    response.contentType = MediaType.APPLICATION_JSON_VALUE
                }
            }
            .exceptionHandling {
                it.accessDeniedHandler { _, response, _ ->
                    val body = objectMapper.writeValueAsString(AccessDeniedFailure())
                    response.status = HttpStatus.FORBIDDEN.value()
                    response.writer.write(body)
                    response.contentType = MediaType.APPLICATION_JSON_VALUE
                }
            }
        return http.build()
    }
}