package com.example.demo

import com.example.demo.database.AccountMapper
import com.example.demo.database.AccountRecord
import com.example.demo.database.insert
import com.example.demo.database.selectByPrimaryKey
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(private val accountMapper: AccountMapper) {

    @GetMapping("/select/{id}")
    fun select(@PathVariable("id") id: Int): AccountRecord? {
        return accountMapper.selectByPrimaryKey(id)
    }

    @PostMapping("/insert")
    fun insert(@RequestBody request: InsertRequest): InsertResponse {
        val record = AccountRecord(request.id, request.name, request.age, request.profile)
        return InsertResponse(accountMapper.insert(record))
    }
}