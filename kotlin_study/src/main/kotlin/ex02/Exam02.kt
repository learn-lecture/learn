package org.example.ex02

fun main() {

    val a: Int = 0
    val b = 10
    val c: Int = 20
    val d: Int? = null

    callFunc(a)
    callFunc(b)
    callFunc(c)
    callFunc(d)
    callFunc()
}

// 메소드는 기본적으로 wrapper형태
// 엘비스 연산자, null 체크, null 이 올 수도 있다.
// 변수?  < null 이야?
// 변수?. < null이 아닐 때
// 변수?: < null일 때
// parameter 값이 없을 경우 default 설정
fun callFunc(i: Int? = 100) {
    // 단순 print
    println("1. $i")

    // 표준 함수
    i?.let { println("2. $it") }
        ?: run { println("2. null") }

    // 변수를 활용
    val temp = if (i == null) "null" else i
    println("3. $temp")

    // null만 활용
    val temp2 = i?:"null"
    println("4. $temp2")

    val temp3 = i?.let { it * 20 } ?: "null"
    println("5. $temp3")

}