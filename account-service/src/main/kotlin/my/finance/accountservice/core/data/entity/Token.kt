package my.finance.accountservice.core.data.entity

import jakarta.persistence.*
import my.finance.accountservice.core.data.entity.User
import java.time.LocalDateTime
import java.util.UUID

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