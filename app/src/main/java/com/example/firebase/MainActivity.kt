package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding : ActivityMainBinding

    // Create instance of Firebase Database
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    // get reference to our data object child  we want
    val myReference : DatabaseReference = database.reference.child("MyUsers")

    // create an ArrayList Object, of type Users,  to hold Users Data
    var userList = ArrayList<Users>()

    // Create an instance of the Adapter class to use to get the data into
    // the arrayList
    lateinit var usersAdapter : UsersAdapter



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

        // hook up the swipe Left & Right delete action
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(
                viewHolder: RecyclerView.ViewHolder,
                direction: Int
            ) {
               val id =usersAdapter.getUserId(viewHolder.adapterPosition)

                myReference.child(id).removeValue()

                Toast.makeText(applicationContext, "The user was deleted",
                    Toast.LENGTH_LONG).show()
            }
        }).attachToRecyclerView(mainBinding.recyclerView)
        // End of ItemTouchHelper actions for recyclerView



        // call data retrieval function
        retrieveDataFromDatabase()

    } // end onCreate function

    fun retrieveDataFromDatabase(){

        myReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                // clear the arrayList before adding the data
                userList.clear()

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

                        // add user data to arrayList
                        userList.add(user)

                    } // end IF

                    // initialise the Adapter object
                    // context , data object
                    usersAdapter = UsersAdapter(this@MainActivity, userList)

                    mainBinding.recyclerView.layoutManager =
                        LinearLayoutManager(this@MainActivity)

                    // set the adapter for the recyclerView
                    mainBinding.recyclerView.adapter = usersAdapter

                } // end FOR action for each user data

            } // end onDataChange function actions

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

} // end class MainActivity