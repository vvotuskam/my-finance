package my.finance.accountservice.user

enum class Role {
    ROLE_ADMIN,
    ROLE_USER
}

inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String): T? =
    runCatching { enumValueOf<T>(name) }.getOrNull()