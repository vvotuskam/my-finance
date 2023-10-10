package my.finance.accountservice.feature.account.rest.dto.response

data class AccountGetResponse(
    val accounts: List<AccountGetByNameResponse>
)