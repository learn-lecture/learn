package org.example.ex04

fun main() {
    // Any == Object
    // 표준 메서드 -> 불변
    val map = mapOf<String, Any>(
        Pair("key1", "value1"),
        "key2" to "value2",
    )

    // generic을 선언하지 않아도 타입 추론 가능
    val mutableMap = mutableMapOf(
        "key1" to "value1",
    )

    mutableMap.put("key2", "value2")
    mutableMap["key3"] = "value3"

    val value1 = mutableMap.get("key1")
    val value2 = mutableMap["key2"]

    // hashMap 또한 map과 동일함.
    val hashMap = hashMapOf<String, Any>()

    // Pair 외에 Triple 객체 또한 존재, 타입 추론 가능
    // Triple<> generic으로 타입 명시 가능
    var triple = Triple(
        first = "",
        second = "",
        third = ""
    )

}