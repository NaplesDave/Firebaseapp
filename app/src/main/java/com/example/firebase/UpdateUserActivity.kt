package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityUpdateUserBinding
import com.google.firebase.database.FirebaseDatabase

class UpdateUserActivity : AppCompatActivity() {

    lateinit var updateUserBinding: ActivityUpdateUserBinding

    // create instance of database in global area
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateUserBinding = ActivityUpdateUserBinding.inflate(layoutInflater)
        val view = updateUserBinding.root
        setContentView(view)

        // update menu bar title
        supportActionBar?.title = "Update User"

        // call the function to get the data and update the screen
        getAndSetData()

        // set a click listener for the update button
        updateUserBinding.buttonUpdateUser.setOnClickListener {

            updateData()

        }

    } // end onCreate actions

    fun getAndSetData(){
        // extract the data from the intent data passed
        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0).toString()
        val email = intent.getStringExtra("email")

        // update the screen values
        updateUserBinding.editTextUpdateName.setText(name)
        updateUserBinding.editTextUpdateAge.setText(age)
        updateUserBinding.editTextUpdateEmail.setText(email)

    }

    fun updateData(){

        // get the data from the screen entry fields
        val updatedName = updateUserBinding.editTextUpdateName.text.toString()
        val updatedAge = updateUserBinding.editTextUpdateAge.text.toString().toInt()
        val updatedEmail = updateUserBinding.editTextUpdateEmail.text.toString()
        // record ID was passed from intent data passed to activity
        val userId = intent.getStringExtra("id").toString()

        val userMap = mutableMapOf<String, Any>()
        userMap["userId"] = userId
        userMap["userName"] = updatedName
        userMap["userAge"] = updatedAge
        userMap["userEmail"] = updatedEmail

        myReference.child(userId).updateChildren(userMap)
            .addOnCompleteListener { task ->

            if (task.isSuccessful){

                Toast.makeText(applicationContext, "The user has been " +
                        "updated", Toast.LENGTH_LONG).show()

                // close the activity , open mainActivity
                finish()

            }


        }




    }
}