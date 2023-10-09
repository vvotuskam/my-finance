package my.finance.authservice.base

interface UseCase<PARAMS, RESPONSE> {

    fun invoke(params: PARAMS): RESPONSE
}