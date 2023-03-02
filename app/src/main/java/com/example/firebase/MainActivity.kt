package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    // Create instance of Firebase Database
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    // get reference to our data object child  we want
    val myReference : DatabaseReference = database.reference.child("MyUsers")



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setup binding to view objects
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        mainBinding.floatingActionButton.setOnClickListener {

            // floating action button clicked, open addUserActivity
        val intent = Intent(this, AddUserActivity::class.java)
        startActivity(intent)
        } // end onClick listener for FAButton

        // call data retrieval function
        retrieveDataFromDatabase()

    } // end onCreate function

    fun retrieveDataFromDatabase(){

        myReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // retrieve the data, loop through database child branch
                for (eachuser in snapshot.children){

                    val user = eachuser.getValue(Users::class.java)

                    // print the data to the logcat window if not null
                    if (user != null){
                        println("userId: ${user.userId}")
                        println("userName: ${user.userName}")
                        println("userAge: ${user.userAge}")
                        println("userEmail: ${user.userEmail}")
                        println("*******************************")
                    } // end IF

                } // end FOR action for each user data

            } // end onDataChange function actions

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

} // end class MainActivity