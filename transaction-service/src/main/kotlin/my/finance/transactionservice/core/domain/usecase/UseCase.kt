package my.finance.transactionservice.core.domain.usecase

interface UseCase<PARAMS, RESPONSE> {

    operator fun invoke(params: PARAMS): RESPONSE
}