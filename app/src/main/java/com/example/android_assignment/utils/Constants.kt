package com.example.android_assignment.utils

import android.util.Patterns
import androidx.core.util.PatternsCompat

object Constants {

    const val BASE_URL = "https://api.nytimes.com/svc/mostpopular/v2/"
    const val FALLBACK_IMAGE = "\"https://static01.nyt.com/images/2023/07/08/opinion/06french-newsletter-image/06french-newsletter-image-thumbStandard.jpg\"\n"
    enum class NewsType {
        EMAILED,
        VIEWED,
        SHARED
    }

    fun validateEmail(email : String) : SignUpResponse {

        if (email.isNullOrEmpty())
            return SignUpResponse.Error("Email cannot be empty")
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches())
            return SignUpResponse.Error("Not a email")

        return SignUpResponse.Success()
    }

    fun validateName(name : String) : SignUpResponse {
        if (name.isNullOrEmpty())
            return SignUpResponse.Error("Name cannot be empty")
        if (name.length < 3)
            return SignUpResponse.Error("Name should be greater than 3")
        return SignUpResponse.Success()
    }

    fun validatePassword(password : String) : SignUpResponse {
        if (password.isNullOrEmpty())
            return SignUpResponse.Error("Password cannot be empty")

        if (password.length < 8)
            return SignUpResponse.Error("Password should be greater than 8")

        if (password.firstOrNull { it.isDigit() } == null)
            return SignUpResponse.Error("Password should contain at-least 1 digit")
        if (password.filter { it.isLetter() }.firstOrNull { it.isUpperCase() } == null)
            return SignUpResponse.Error("Password should contain at-least 1 uppercase letter")

        if (password.filter { it.isLetter() }.firstOrNull { it.isLowerCase() } == null)
            return SignUpResponse.Error("Password should contain at-least 1 lowercase letter")

        if (password.firstOrNull { !it.isLetterOrDigit() } == null)
            return SignUpResponse.Error("Password should contain at-least 1 special char")

        return SignUpResponse.Success()

    }
}