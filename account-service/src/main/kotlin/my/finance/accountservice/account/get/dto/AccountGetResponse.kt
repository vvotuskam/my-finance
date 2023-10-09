package my.finance.accountservice.account.get.dto

import my.finance.accountservice.account.getbyid.dto.AccountGetByIdResponse

data class AccountGetResponse(
    val accounts: List<AccountGetByIdResponse>
)