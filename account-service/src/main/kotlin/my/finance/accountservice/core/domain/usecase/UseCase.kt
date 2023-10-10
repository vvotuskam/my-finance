package my.finance.accountservice.core.domain.usecase

interface UseCase<PARAMS, RESPONSE> {

    operator fun invoke(params: PARAMS): RESPONSE
}