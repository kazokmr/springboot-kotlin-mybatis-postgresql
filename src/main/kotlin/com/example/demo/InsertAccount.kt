package com.example.demo

class InsertAccount {
}

data class AccountRequest(
    val id: Int? = null,
    val name: String? = null,
    val age: Int? = null,
    val profile: String? = null
)

data class InsertResponse(val count: Int)