package my.finance.authservice.core.data.user

import jakarta.persistence.*
import my.finance.authservice.core.data.token.Token
import java.util.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val email: String,

    val password: String,

    @Enumerated(EnumType.STRING)
    val role: Role,

    @OneToMany(mappedBy = "user")
    val tokens: List<Token>
)