package my.finance.authservice.core.data.otp

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "otp_codes")
data class OtpCode(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val code: String,

    @Enumerated(EnumType.STRING)
    val status: OtpStatus,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val expiresAt: LocalDateTime,

    val email: String
)