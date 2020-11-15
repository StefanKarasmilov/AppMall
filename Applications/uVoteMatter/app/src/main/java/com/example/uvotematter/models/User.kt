package com.example.uvotematter.models

data class User(
    val id: String?,
    val nickname: String
) {
    constructor(): this("", "")
}