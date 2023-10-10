package my.finance.accountservice.account.get.dto

import my.finance.accountservice.account.getbyname.dto.AccountGetByNameResponse

data class AccountGetResponse(
    val accounts: List<AccountGetByNameResponse>
)