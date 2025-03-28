package org.example.ex11

fun main() {

    // let과 달리 반환되는 객체 == also의 주체
    // logging 및 부가적인 작업 할 때
    // let과 동일하지만 반환이 다름
    val userDto = UserDto("test").also { dto ->
        println(dto)
        dto.name = "rename"
        UserResponse("test1")
    }

    println(userDto)

}