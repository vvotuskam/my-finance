package my.finance.authservice.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import kotlin.io.path.createTempDirectory

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(

) {

    @Bean
    fun passwordEncoder(): PasswordEncoder = NoOpPasswordEncoder.getInstance()

    @Bean
    fun authenticationProvider(
        encoder: PasswordEncoder,
        service: UserDetailsService,
    ): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(encoder)
        provider.setUserDetailsService(service)
        return provider
    }

    @Bean
    fun authenticationManager(
        config: AuthenticationConfiguration,
    ): AuthenticationManager {
        return config.getAuthenticationManager()
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http
            .cors { cors -> cors.disable() }
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth -> auth.anyRequest().permitAll() }

        return http.build()
    }
}