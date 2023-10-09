package my.finance.authservice.base

interface UseCase<PARAMS, RESPONSE> {

    operator fun invoke(params: PARAMS): RESPONSE
}