package org.example.ex09

fun main() {

    when("") {
        "" -> println("")
        "MASTER", "ADMIN" -> println("master")
        "USER" -> println("user")
        else -> println("default")
    }

    // 객체 단위로도 가능
    val any: Any = "";
    when (any) {
        is String -> println("String")
        is Int -> println("Int")
        is Boolean -> println("Boolean")
    }

    val exception = RuntimeException()
    when (exception) {
        is NullPointerException -> {
            // nullPointer에 대한 type casting이 되어 있음.
        }
        is RuntimeException -> {
            // RuntimeException 에 대한 type casting이 되어 있음.
        }
    }

    // 지역변수 형태로 사용 가능
    val number = 2;
    when (val n = number % 2) {
        0 -> println("0")
        1 -> println("1")
    }

    // 내부에서 바로 수행 가능
    val result = when {
        number % 2 == 0 -> 0
        else -> 1
    }
    println(result)

}