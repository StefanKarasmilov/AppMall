package com.example.uvotematter.models

data class Vote(
    val id: String,
    val nickname: String,
    val points: String
) {
    constructor() : this("", "", "")
}