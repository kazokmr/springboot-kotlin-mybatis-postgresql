package com.example.demo

class InsertAccount {
}

data class InsertRequest(val id: Int, val name: String, val age: Int, val profile: String)

data class InsertResponse(val count: Int)