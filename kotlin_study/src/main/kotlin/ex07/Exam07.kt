package org.example.ex07

import java.util.Objects

fun main() {
    // named arguments
    val user = User(age = 10)
    user.userName = "test"
    user.userEmail = "test"
    println(user)

    val user2 = user.copy(name = "test2")
    println(user2)

    println("${Objects.toIdentityString(user)} , ${Objects.toIdentityString(user2)}")
}