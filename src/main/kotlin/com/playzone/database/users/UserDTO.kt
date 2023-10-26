package com.playzone.database.users


// DATA TRANSFER OBJECT
class UserDTO(
    val login: String,
    val email: String?,
    val password: String,
    val username: String,
)