package com.example.aula_mvvm_02

class PersonRepository {
    fun login(email: String, password: String): Boolean {
        return (email == "admin" && password == "pass")
    }
}