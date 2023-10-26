package my.finance.authservice.core.config.security

import my.finance.authservice.core.config.properties.JwtDecoderProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val decoderProps: JwtDecoderProperties
) {

    @Bean
    fun jwtDecoder(): JwtDecoder = JwtDecoders.fromIssuerLocation(decoderProps.issuerUri)

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtConverter: JwtConverter,
    ): SecurityFilterChain {

        http
            .cors { cors -> cors.disable() }
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth -> auth.anyRequest().permitAll() }
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt {
                    it.jwtAuthenticationConverter(jwtConverter)
                }
            }
        return http.build()
    }
}