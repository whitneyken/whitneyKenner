package com.example

import org.jetbrains.exposed.dao.id.IntIdTable

object Post : IntIdTable() {
    val content = text("content")
    val time = long("time")
}