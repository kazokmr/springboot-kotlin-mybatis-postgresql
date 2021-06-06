/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.demo.database

import com.example.demo.database.AccountDynamicSqlSupport.Account
import com.example.demo.database.AccountDynamicSqlSupport.Account.age
import com.example.demo.database.AccountDynamicSqlSupport.Account.id
import com.example.demo.database.AccountDynamicSqlSupport.Account.name
import com.example.demo.database.AccountDynamicSqlSupport.Account.profile
import com.example.demo.database.AccountRecord
import org.mybatis.dynamic.sql.SqlBuilder.isEqualTo
import org.mybatis.dynamic.sql.util.kotlin.*
import org.mybatis.dynamic.sql.util.kotlin.mybatis3.*

fun AccountMapper.count(completer: CountCompleter) =
    countFrom(this::count, Account, completer)

fun AccountMapper.delete(completer: DeleteCompleter) =
    deleteFrom(this::delete, Account, completer)

fun AccountMapper.deleteByPrimaryKey(id_: Int) =
    delete {
        where(id, isEqualTo(id_))
    }

fun AccountMapper.insert(record: AccountRecord) =
    insert(this::insert, record, Account) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(age).toProperty("age")
        map(profile).toProperty("profile")
    }

fun AccountMapper.insertMultiple(records: Collection<AccountRecord>) =
    insertMultiple(this::insertMultiple, records, Account) {
        map(id).toProperty("id")
        map(name).toProperty("name")
        map(age).toProperty("age")
        map(profile).toProperty("profile")
    }

fun AccountMapper.insertMultiple(vararg records: AccountRecord) =
    insertMultiple(records.toList())

fun AccountMapper.insertSelective(record: AccountRecord) =
    insert(this::insert, record, Account) {
        map(id).toPropertyWhenPresent("id", record::id)
        map(name).toPropertyWhenPresent("name", record::name)
        map(age).toPropertyWhenPresent("age", record::age)
        map(profile).toPropertyWhenPresent("profile", record::profile)
    }

private val columnList = listOf(id, name, age, profile)

fun AccountMapper.selectOne(completer: SelectCompleter) =
    selectOne(this::selectOne, columnList, Account, completer)

fun AccountMapper.select(completer: SelectCompleter) =
    selectList(this::selectMany, columnList, Account, completer)

fun AccountMapper.selectDistinct(completer: SelectCompleter) =
    selectDistinct(this::selectMany, columnList, Account, completer)

fun AccountMapper.selectByPrimaryKey(id_: Int) =
    selectOne {
        where(id, isEqualTo(id_))
    }

fun AccountMapper.update(completer: UpdateCompleter) =
    update(this::update, Account, completer)

fun KotlinUpdateBuilder.updateAllColumns(record: AccountRecord) =
    apply {
        set(id).equalTo(record::id)
        set(name).equalTo(record::name)
        set(age).equalTo(record::age)
        set(profile).equalTo(record::profile)
    }

fun KotlinUpdateBuilder.updateSelectiveColumns(record: AccountRecord) =
    apply {
        set(id).equalToWhenPresent(record::id)
        set(name).equalToWhenPresent(record::name)
        set(age).equalToWhenPresent(record::age)
        set(profile).equalToWhenPresent(record::profile)
    }

fun AccountMapper.updateByPrimaryKey(record: AccountRecord) =
    update {
        set(name).equalTo(record::name)
        set(age).equalTo(record::age)
        set(profile).equalTo(record::profile)
        where(id, isEqualTo(record::id))
    }

fun AccountMapper.updateByPrimaryKeySelective(record: AccountRecord) =
    update {
        set(name).equalToWhenPresent(record::name)
        set(age).equalToWhenPresent(record::age)
        set(profile).equalToWhenPresent(record::profile)
        where(id, isEqualTo(record::id))
    }