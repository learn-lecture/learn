package org.example.ex05

import java.util.function.Predicate

fun main(args: Array<String>) {

    val numbers = listOf(1, 2, 3, 4, 5)

    numbers.filter { it % 2 == 0 }

    // 익명 클래스는 object keyowrd 사용
    // lambda 식으로 개선가능
    val pred = object : Predicate<Int> {
        override fun test(t: Int): Boolean {
            return t%2 == 0
        }
    }

    // Java 구현체인 stream 으로 filter
    numbers.stream().filter(pred)
    numbers.stream().filter {it % 2 == 0}

    val add = { x: Int, y: Int -> x + y }
    println(add.invoke(1, 2))
    println(add(3, 4))

    val add2 = fun(x: Int, y: Int) = x + y
    println(add2(5, 6))

    val add3 = fun(x: Int, y: Int): Int { return x + y }
    println(add3(7, 8))

    lambda(9, 10, add)

}

fun lambda(x: Int, y: Int, method: (Int, Int) -> Int) {
    println(method(x, y))
}