package com.hgm.sharingdata.sample.util

data class Session(
    val user: User,
    val token: String,
    val expiresAt: Long
)

data class User(
    val firstName: String,
    val lastName: String,
    val email: String
)