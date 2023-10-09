package my.finance.authservice.base

interface Mapper<REQUEST, PARAMS> {

    fun convert(request: REQUEST): PARAMS
}