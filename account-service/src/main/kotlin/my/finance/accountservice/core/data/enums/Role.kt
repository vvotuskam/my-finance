package my.finance.accountservice.core.data.enums

enum class Role {
    ROLE_ADMIN,
    ROLE_USER
}

inline fun <reified T : Enum<T>> enumValueOfOrNull(name: String): T? =
    runCatching { enumValueOf<T>(name) }.getOrNull()