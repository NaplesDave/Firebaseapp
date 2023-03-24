package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase.databinding.ActivityPhoneBinding

class PhoneActivity : AppCompatActivity() {

    lateinit var phoneBinding : ActivityPhoneBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        phoneBinding = ActivityPhoneBinding.inflate(layoutInflater)
        val view = phoneBinding.root

        setContentView(view)

        phoneBinding.buttonSendSMSCode.setOnClickListener {


        }

        phoneBinding.buttonVerify.setOnClickListener {


        }
    }
}