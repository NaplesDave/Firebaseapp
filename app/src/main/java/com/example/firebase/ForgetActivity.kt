package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityForgetBinding
import com.google.firebase.auth.FirebaseAuth

class ForgetActivity : AppCompatActivity() {

    lateinit var forgetBinding : ActivityForgetBinding

    val auth : FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        forgetBinding = ActivityForgetBinding.inflate(layoutInflater)
        val view = forgetBinding.root

        setContentView(view)

        forgetBinding.buttonReset.setOnClickListener {
            val email = forgetBinding.editTextReset.text.toString()

            auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(applicationContext, "We sent a password " +
                            "reset mail to your mail address", Toast
                        .LENGTH_LONG).show()

                    finish()
                } // end lambda func

            } // end reset feature

        }

    } // end of onCreate func

} // end of ForgetActivity class