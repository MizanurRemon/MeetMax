package com.meetmax.common.util

import java.util.regex.Pattern

fun validateEmail(input: String): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )
    return emailPattern.matcher(input).matches()
}