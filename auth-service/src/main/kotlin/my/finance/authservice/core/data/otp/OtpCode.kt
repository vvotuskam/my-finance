package my.finance.authservice.core.data.otp

import jakarta.persistence.*
import my.finance.authservice.core.data.user.User
import java.time.LocalDateTime
import java.util.UUID

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)