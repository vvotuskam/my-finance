package my.finance.authservice.core.data.token

import jakarta.persistence.*
import my.finance.authservice.core.data.user.User
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tokens")
data class Token(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val jwt: String,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User
)