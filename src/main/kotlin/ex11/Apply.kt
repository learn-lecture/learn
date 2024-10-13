package org.example.ex11

fun main() {
    // 생성자 패턴
    // also와 마찬가지로 수신받은 객체를 반환
    // 수신 객체 이름 지정 불가
    // this 키워드로 내부에 동작
    // this 객체의 메서드를 apply 내부 스코프에서 동작가능
    UserDto().apply {
        name = "test"
    }

}