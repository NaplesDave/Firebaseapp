package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var editTextName : EditText
    lateinit var buttonSend : Button
    lateinit var textViewName : TextView

    val dataBase : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = dataBase.reference.child("users")
    val reference2 : DatabaseReference = dataBase.reference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName =  findViewById(R.id.editTextName)
        buttonSend = findViewById(R.id.buttonSend)
        textViewName = findViewById(R.id.textViewName)

        reference2.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

            val realName : String = snapshot.child("users").child("name").value as String

                textViewName.text = realName
            } // end onDataChanged fun

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            } // end onCancelled func
        }) // end reference2 block


        buttonSend.setOnClickListener {

            val userName = editTextName.text.toString()

            reference.child("userName").setValue(userName)


        }

    }
}