package com.example.android_assignment.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.android_assignment.BuildConfig
import com.example.android_assignment.utils.SignUpResponse
import com.example.android_assignment.databinding.ActivitySignupBinding
import com.example.android_assignment.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btNext.setOnClickListener {

            val email = binding.etEmail.text.toString()
            val pass = binding.etPass.text.toString()
            val name = binding.etName.text.toString()

            val validEmail = Constants.validateEmail(email)

            if (validEmail is SignUpResponse.Error){
                Toast.makeText(this, validEmail.errorMsg , Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val validName = Constants.validateName(name)

            if (validName is SignUpResponse.Error){
                Toast.makeText(this, validName.errorMsg, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val validPas = Constants.validatePassword(pass)

            if (validPas is SignUpResponse.Error){
                Toast.makeText(this, validPas.errorMsg, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            moveToHomeScreen()

        }
    }

    private fun moveToHomeScreen() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}