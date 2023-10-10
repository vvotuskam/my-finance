package my.finance.accountservice.core.domain.usecase

interface UseCase<PARAMS, RESPONSE> {

    fun invoke(params: PARAMS): RESPONSE
}