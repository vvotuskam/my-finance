package my.finance.authservice.core.domain.usecase

interface UseCase<PARAMS, RESPONSE> {

    operator fun invoke(params: PARAMS): RESPONSE
}