package my.finance.authservice.core.rest.mapper

interface Mapper<REQUEST, PARAMS> {

    fun convert(request: REQUEST): PARAMS
}