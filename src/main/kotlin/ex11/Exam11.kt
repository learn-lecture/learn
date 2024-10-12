package org.example.ex11

import java.time.LocalDateTime

fun main() {

    val now = LocalDateTime.now()
    val localDateTime: LocalDateTime?=null
    val result1 = now?.let { localDateTime ->
        println("letScope $localDateTime")
        "let let let"
    }?: LocalDateTime.now()

    val result2 = localDateTime?.let { localDateTime ->
        println("letScope $localDateTime")
        "let let let"
    }?: LocalDateTime.now()

    println("letScope Result $result1")
    println("null Result $result2")

    println("Response ${UserDto("test").let { logic(it) }}")

    val usersDto = listOf(UserDto("test1"), UserDto("test2"))
    val responses = usersDto.stream()
        .map { logic(it) }
        .toList()
    println(responses)
}

fun logic(userDto: UserDto?): UserResponse? {
    return userDto?.let {
        println("Dto $it")
        UserEntity(name = it.name)
    }?. let {
        println("Entity $it")
        UserResponse(it.name)
    }
}

data class UserDto(
    var name: String?=null,
)

data class UserEntity(
    var name: String?=null
)

data class UserResponse(
    var userName: String?=null
)