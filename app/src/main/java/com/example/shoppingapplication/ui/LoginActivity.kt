package com.example.shoppingapplication.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppingapplication.R
import com.example.shoppingapplication.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth


    private fun isVerifiedUser() {
        auth.signInWithEmailAndPassword(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("LoginActivity", "loginUserWithEmail:success")
                val user = auth.currentUser
                goToHomeActivity()
            } else {
                // If sign in fails, display a message to the user.
                Log.w("LoginActivity", "createUserWithEmail:failure", task.exception)
                Toast.makeText(
                    baseContext,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            isVerifiedUser()
        }

        binding.btnGoogleLogin.setOnClickListener {

        }
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user != null) goToHomeActivity()
    }

    private fun goToHomeActivity() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        ).also { this@LoginActivity.finish() }
    }
}