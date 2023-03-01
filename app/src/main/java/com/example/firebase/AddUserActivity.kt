package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityAddUserBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddUserActivity : AppCompatActivity() {

    lateinit var addUserBinding: ActivityAddUserBinding

    val database :FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference : DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setup view binding to objects
        addUserBinding = ActivityAddUserBinding.inflate(layoutInflater)
        val view = addUserBinding.root
        setContentView(view)

        // Set the action Bar Title for adding user
        supportActionBar?.title = "Add User"

        addUserBinding.buttonAddUser.setOnClickListener {

            addUserToDatabase()

        }

    } // end onCreate function

    fun addUserToDatabase(){

        val name : String = addUserBinding.editTextName.text.toString()
        val age : Int = addUserBinding.editTextAge.text.toString().toInt()
        val email : String = addUserBinding.editTextEmail.text.toString()

        // create the id record key for the data set push().key.toString()
        val id : String = myReference.push().key.toString()

        // create the data object
        val user = Users(id, name, age, email)

        // save the data to Firebase
        myReference.child(id).setValue(user).addOnCompleteListener { task ->

            if (task.isSuccessful){
                // show a message, data saved
                Toast.makeText(applicationContext,
                "The new user has been added to the database",
                Toast.LENGTH_LONG).show()

                // close the activity
                finish()

            } else{

                // data save failed , show a message
                Toast.makeText(applicationContext,
                    task.exception.toString(),
                    Toast.LENGTH_LONG).show()
            }
        } //end myReference operation to save data

    }

} // end class AddUserActivity