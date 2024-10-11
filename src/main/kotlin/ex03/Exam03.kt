package org.example.ex03

fun main() {

    // list도 가변과 불변이 존재
    // 가변 mutable, 불변 immutable
    val mutableUsers = mutableListOf<User>()
    mutableUsers.add(User("1", 10))
    mutableUsers.add(User("2", 20))
    mutableUsers.add(User("3", 30))

    // 초기화 시 모두 할당해야함
    val immutableUsers = listOf<User>(
        User("4", 40)
    )

    for (element in immutableUsers) {
        println(element)
    }

    mutableUsers.forEach { println ("$it") }

    mutableUsers.forEachIndexed { index, user ->
        println("$index: $user")
    }

    // 고차함수
    mutableUsers.forEachIndexed(fun(index: Int, user: User) {
        println("$index: $user")
    })

    for ((index, user) in mutableUsers.withIndex()) {
        println("$index: $user")
    }

}

class User(
    var name: String,
    var age: Int
)