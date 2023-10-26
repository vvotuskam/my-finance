package my.finance.authservice.core.config.source.request

data class CreateUserRequest(
    val email: String,
    val username: String = email,
    val enabled: Boolean = true,
    val emailVerified: Boolean = true,
    val credentials: Array<Credentials>,
    val realmRoles: Array<String>
) {

    data class Credentials(
        val type: String = "password",
        val temporary: Boolean = false,
        val value: String,
    )

    constructor(email: String, password: String, role: String = "USER") : this(
        email = email,
        credentials = arrayOf(
            Credentials(
                value = password
            )
        ),
        realmRoles = arrayOf(role)
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CreateUserRequest

        if (email != other.email) return false
        if (username != other.username) return false
        if (enabled != other.enabled) return false
        if (emailVerified != other.emailVerified) return false
        if (!credentials.contentEquals(other.credentials)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + username.hashCode()
        result = 31 * result + enabled.hashCode()
        result = 31 * result + emailVerified.hashCode()
        result = 31 * result + credentials.contentHashCode()
        return result
    }
}