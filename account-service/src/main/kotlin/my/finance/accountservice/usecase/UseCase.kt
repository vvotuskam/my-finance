package my.finance.accountservice.usecase

interface UseCase<PARAMS, RESPONSE> {

    fun invoke(params: PARAMS): RESPONSE
}