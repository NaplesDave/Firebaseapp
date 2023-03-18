package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    lateinit var signupBinding: ActivitySignupBinding

    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = signupBinding.root

        setContentView(view)

        signupBinding.buttonSignupUser.setOnClickListener {

            val userEmail = signupBinding.editTextEmailSignup.text.toString()
            val userPassword = signupBinding.editTextPasswordSignup.text.toString()

            signupWithFirebase(userEmail, userPassword)
        }
    }  // end onCreate function

    fun signupWithFirebase(userEmail : String, userPassword : String){
        //
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->

                if(task.isSuccessful){
                    Toast.makeText(applicationContext, "Your account has been" +
                            " created", Toast.LENGTH_LONG).show()
                    finish()

                }else {
                    // show toast why it failed
                    Toast.makeText(applicationContext, task.exception?.toString(), Toast
                        .LENGTH_LONG).show()

                }
        }


    }

}