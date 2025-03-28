package org.example.ex11

import java.time.LocalDateTime

fun main() {

    // run은 apply에서 반환 가능하다.
    // let과 달리 this로 접근 가능
    var userDto = UserDto("").run {
        name = "test"
        "return"
    }

    println("return: $userDto")

    val x = 10
    val sum = run {
        val x = 2
        val y = 3
        x + y
    }

    println("sum: $sum")

}