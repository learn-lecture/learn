package org.example.ex06

fun main() {
    val dog = Dog()
    dog.eat()
    dog.bark()
    dog.test()

    println(dog.getAge())
    dog.setAge(10)
    println(dog.getAge())
}

interface Bark {

    fun bark()

}

abstract class Animal(
    private val name: String?=""
): Bark {

    init {
        println("init $name")
    }

    fun eat() {
        println("$name eat")
    }

}

interface Poly {

    fun test();

}

class Dog(
    private val name:String?=null,
) : Animal(name), Poly {

    private var age: Int=0 // Default null

    override fun bark() {
        println("$name bark")
    }

    override fun test() {
        println("polymorphism")
    }

    fun setAge(age: Int) {
        this.age = age
    }

    fun getAge(): Int {
        return this.age;
    }

}