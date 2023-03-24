package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    var auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root

        setContentView(view)

        loginBinding.buttonSignin.setOnClickListener {

            val userEmail = loginBinding.editTextEmailSignin.text.toString()
            val userPassword = loginBinding.editTextPasswordSignin.text.toString()
            // call sign in function
            signinWithFirebase(userEmail, userPassword)

        }

        loginBinding.buttonSignup.setOnClickListener {

            val intent = Intent(this@LoginActivity, SignupActivity::class.java)
            startActivity(intent)

        }

        loginBinding.buttonForgot.setOnClickListener {

            val intent = Intent(this, ForgetActivity::class.java)
            startActivity(intent)

        }

        loginBinding.buttonSigninWithPhoneNumber.setOnClickListener {

            val intent = Intent(this, PhoneActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    fun signinWithFirebase(userEmail : String, userPassword : String){

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener {task ->
            if(task.isSuccessful){
                // show taost message
                Toast.makeText(applicationContext,"Login is successful",
                    Toast.LENGTH_LONG).show()
                // start MainActivity
                 val intent = Intent(this@LoginActivity, MainActivity::class
                     .java)
                startActivity(intent)
                // close this activity
                finish()

            }else {
                // show message why failed
                Toast.makeText(applicationContext,task.exception.toString(),
                    Toast.LENGTH_LONG).show()

            }
        }

    }

    override fun onStart() {
        super.onStart()

        // check / get current user info if logged in
        val user = auth.currentUser

        if (user != null) {
            // open mainActivity
            val intent = Intent(this@LoginActivity, MainActivity::class
                .java)
            startActivity(intent)
            // close this activity
            finish()
        }
    }
}