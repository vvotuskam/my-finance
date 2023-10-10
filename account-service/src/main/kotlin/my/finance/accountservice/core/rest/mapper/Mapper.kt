package my.finance.accountservice.core.rest.mapper

interface Mapper<REQUEST, PARAMS> {

    fun convert(request: REQUEST): PARAMS
}