/*
 * Auto-generated file. Created by MyBatis Generator
 */
package com.example.demo.database

import java.sql.JDBCType
import org.mybatis.dynamic.sql.SqlTable

object AccountDynamicSqlSupport {
    object Account : SqlTable("public.account") {
        val id = column<Int>("id", JDBCType.INTEGER)

        val name = column<String>("name", JDBCType.VARCHAR)

        val age = column<Int>("age", JDBCType.INTEGER)

        val profile = column<String>("profile", JDBCType.VARCHAR)
    }
}