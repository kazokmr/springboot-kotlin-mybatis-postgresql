package com.example.demo

import com.example.demo.database.AccountDynamicSqlSupport.Account
import com.example.demo.database.AccountMapper
import com.example.demo.database.AccountRecord
import com.example.demo.database.count
import com.example.demo.database.delete
import com.example.demo.database.deleteByPrimaryKey
import com.example.demo.database.insert
import com.example.demo.database.insertMultiple
import com.example.demo.database.select
import com.example.demo.database.selectByPrimaryKey
import com.example.demo.database.update
import com.example.demo.database.updateByPrimaryKeySelective
import com.example.demo.database.updateSelectiveColumns
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.SqlBuilder.isGreaterThanOrEqualTo
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("account")
class AccountController(private val accountMapper: AccountMapper) {

    @GetMapping("/select/{id}")
    fun select(@PathVariable("id") id: Int): AccountRecord? {
        return accountMapper.selectByPrimaryKey(id)
    }

    @GetMapping("/select/name/{name}")
    fun selectByName(@PathVariable("name") name: String): List<AccountRecord> {
        return accountMapper.select { where(Account.name, isEqualTo(name)) }
    }

    @GetMapping("/select/age/{age}")
    fun selectByAgeIsGreaterThanOrEqual(@PathVariable("age") age: Int): List<AccountRecord> {
        return accountMapper.select { where(Account.age, isGreaterThanOrEqualTo(age)) }
    }

    @GetMapping("/select/count/age/{age}")
    fun selectCountByAgeIsGreaterThanOrEqual(@PathVariable("age") age: Int): Map<String, Long> {
        return mapOf("count" to accountMapper.count { where(Account.age, isGreaterThanOrEqualTo(age)) })
    }

    @GetMapping("/select/count/all")
    fun selectCountAllRows(): Map<String, Long> {
        return mapOf("count" to accountMapper.count { allRows() })
    }

    @PostMapping("/insert")
    fun insert(@RequestBody request: AccountRequest): InsertResponse {
        val record = AccountRecord(request.id, request.name, request.age, request.profile)
        return InsertResponse(accountMapper.insert(record))
    }

    @PostMapping("/insert/multiple")
    fun insertMultiple(@RequestBody requests: List<AccountRequest>): InsertResponse {
        val list = accountMapper.insertMultiple(requests.map { AccountRecord(it.id, it.name, it.age, it.profile) })
        return InsertResponse(list)
    }

    @PutMapping("/update")
    fun update(@RequestBody request: AccountRequest): Map<String, Int> {
        val record = AccountRecord(request.id, request.name, request.age, request.profile)
        return mapOf("count" to accountMapper.updateByPrimaryKeySelective(record))
    }

    @PutMapping("/update/{id}")
    fun updateById(@PathVariable("id") id: Int, @RequestParam("profile") profile: String): Map<String, String> {
        val count = accountMapper.update {
            set(Account.profile).equalTo(profile)
            where(Account.id, isEqualTo(id))
        }
        return mapOf("result" to "${count}行のレコードを更新しました")
    }

    @PutMapping("/update/name/{name}")
    fun updateProfileByName(
        @PathVariable("name") name: String,
        @RequestParam("profile") profile: String
    ): Map<String, String> {
        val count = accountMapper.update {
            updateSelectiveColumns(AccountRecord(profile = profile))
            where(Account.name, isEqualTo(name))
        }
        return mapOf("result" to "${count}行のレコードを更新しました")
    }

    @DeleteMapping("/delete/{id}")
    fun deleteById(@PathVariable("id") id: Int): Map<String, String> {
        val count = accountMapper.deleteByPrimaryKey(id)
        return mapOf("result" to "${count}行のレコードを削除しました")
    }

    @DeleteMapping("/delete/name/{name}")
    fun deleteByName(@PathVariable("name") name: String): Map<String, String> {
        val count = accountMapper.delete {
            where(Account.name, isEqualTo(name))
        }
        return mapOf("result" to "${count}行のレコードを削除しました")
    }
}