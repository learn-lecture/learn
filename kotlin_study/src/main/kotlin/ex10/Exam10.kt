package org.example.ex10

fun main() {
    val user = ExamUser(name = "abcd")
    exam10(user)
}

fun exam10(examUser: ExamUser?) {
    examUser?.let {
        it.name?.let { name -> println(name) }
    }?: run {
        // null
    }

    examUser?.let {
        if (!it.name.isNullOrBlank()) {
            println(it.name)
        }
    }

    // 확장 메소드를 활용
    if (examUser.isNotNull() && examUser?.name.isNotNullOrBlank()) {
        println(examUser?.name)
    }
}

data class ExamUser(
    var name: String?=null
)

// kotlin 모든 조상의 부모 = Any, No Object
fun Any?.isNotNull():Boolean = this != null

// extensions function
fun String?.isNotNullOrBlank(): Boolean {
    return !this.isNullOrBlank();
}