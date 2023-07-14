package com.example.android_assignment.utils

sealed class SignUpResponse(val errorMsg: String? = null) {

    class Success() : SignUpResponse(null)
    class Error(errorMsg : String) : SignUpResponse(errorMsg)
}