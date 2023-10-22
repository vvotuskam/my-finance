package my.finance.transactionservice.core.rest.mapper

interface Mapper<REQUEST, PARAMS> {

    fun convert(request: REQUEST): PARAMS
}