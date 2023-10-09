package my.finance.accountservice.mapper

interface Mapper<REQUEST, PARAMS> {

    fun convert(request: REQUEST): PARAMS
}