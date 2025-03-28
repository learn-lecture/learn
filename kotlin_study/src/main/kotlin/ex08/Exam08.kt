package org.example.ex08

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main(args: Array<String>) {
    Exam08(Store())
    println(DateTimeUtil().localDateTimeToString())
}

data class Store(
    var registerAt: LocalDateTime ?= null
)

class Exam08(store: Store) {

    init {
        val strLocalDateTime = toLocalDateTimeString(store.registerAt)
        println(strLocalDateTime)

        println(toLocalDateTimeString())
    }

    fun toLocalDateTimeString(
        date: LocalDateTime?=LocalDateTime.of(2024, 1, 1, 1, 1)
    ): String {
        val temp = date?:LocalDateTime.now()
        return temp.format(DateTimeFormatter.ofPattern("yyyy MM dd"))
    }

}

class DateTimeUtil {

    private val KST_FORMAT = "yyyy년 MM월 dd일 HH시 mm분 ss초"

    fun localDateTimeToString(
        localDateTime: LocalDateTime?=LocalDateTime.now(),
        pattern: String?=KST_FORMAT
    ): String {
        val temp = localDateTime?:LocalDateTime.now()
        return temp.format(
            pattern?.let { DateTimeFormatter.ofPattern(it) }
        )
    }

}