package org.example.ex07

data class User(
    private var name: String?=null,
    private var email: String?=null,
    private var age: Int?=null
) {

    var userName: String?
        get() = name
        set(value) {
            name = value
        }

    var userEmail: String?
        get() = this.email
        set(value) {
            this.email = value
        }

    var userAge: Int?
        get() = this.age
        set(value) {
            this.age = value
        }

}