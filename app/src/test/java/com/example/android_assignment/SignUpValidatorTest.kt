package com.example.android_assignment

import com.bumptech.glide.util.Util
import com.example.android_assignment.utils.Constants
import com.example.android_assignment.utils.SignUpResponse
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class SignUpValidatorTest {


    //Email validate - Standard Email Format
    @Test
    fun `non-standard email return false`() {
        val result = Constants.validateEmail(
            "shivam.com"
        )
        assertThat(result is SignUpResponse.Error).isTrue()
    }

    @Test
    fun `standard email return true` () {
        val result = Constants.validateEmail(
            "shivam.agrawal@gmail.com"
        )
        assertThat(result is SignUpResponse.Success).isTrue()
    }

    @Test
    fun `empty email return false` () {
        val result = Constants.validateEmail(
            ""
        )
        assertThat(result is SignUpResponse.Error).isTrue()
    }

    //Validate Password
    //Password should contain 1 upper, 1 lower, 1 special, 1 digit and greater than 8

    @Test
    fun `empty password return false`() {
        val result = Constants.validatePassword(
            ""
        )

        assertThat(result is SignUpResponse.Error).isTrue()
    }

    @Test
    fun `password missing special char return false`() {
        val result = Constants.validatePassword(
            "infdQ89Ns"
        )

        assertThat(result is SignUpResponse.Error).isTrue()
    }

    @Test
    fun `password less than length 8 return false`() {
        val result = Constants.validatePassword(
            "infdQ#8"
        )

        assertThat(result is SignUpResponse.Error).isTrue()
    }

    @Test
    fun `valid password return true`() {
        val result = Constants.validatePassword(
            "infdQ#8Hk"
        )

        assertThat(result is SignUpResponse.Success).isTrue()
    }

    //Validate Name

    @Test
    fun `empty name returns false` () {
        val result = Constants.validateName(
            ""
        )
        assertThat(result is SignUpResponse.Error).isTrue()
    }

    @Test
    fun `name less than length 3 returns false` () {
        val result = Constants.validateName(
            "sh"
        )
        assertThat(result is SignUpResponse.Error).isTrue()
    }

    @Test
    fun `valid name returns true` () {
        val result = Constants.validateName(
            "shivam"
        )
        assertThat(result is SignUpResponse.Success).isTrue()
    }





}