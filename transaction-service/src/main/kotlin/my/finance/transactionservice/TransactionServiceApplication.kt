package my.finance.transactionservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TransactionServiceApplication

fun main(args: Array<String>) {
    runApplication<TransactionServiceApplication>(*args)
}
