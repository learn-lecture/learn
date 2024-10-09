package org.example.ex01

fun main() {
    // 변수 선언 2가지 방식
    // var = 가변, (val, final) = 불변(immutable)
    val name: String = "홍길동"
    var _name: String = "홍길동"
    var name_ = "홍길동"

    println("사용자 이름: $name")
    // 아래와 같은 꼴도 됨.
    // println("사용자 이름: ${if(true) name else name}")

    // primitive type 없음
    var i = 10
    var _i: Int = 10

    var d: Double = 20.0
    var _d: Double

    var f:Float = 10f

    var b:Boolean = true
}