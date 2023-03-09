package com.example.firebase

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.UsersItemBinding

class UsersAdapter(var context : Context, var userList : ArrayList<Users>) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>()  {

    inner class UsersViewHolder(val adapterBinding : UsersItemBinding)
        : RecyclerView.ViewHolder(adapterBinding.root){}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UsersViewHolder {
        val binding = UsersItemBinding.inflate(LayoutInflater.from(parent
            .context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.adapterBinding.textViewName.text = userList[position].userName
        holder.adapterBinding.textViewAge.text = userList[position].userAge.toString()
        holder.adapterBinding.textViewEmail.text = userList[position].userEmail

        // Add click listener to layout card
        holder.adapterBinding.linearLayout.setOnClickListener {
            val intent = Intent(context, UpdateUserActivity::class.java)
            // attach the data to the intent
            intent.putExtra("id", userList[position].userId)
            intent.putExtra("name", userList[position].userName)
            intent.putExtra("age", userList[position].userAge)
            intent.putExtra("email", userList[position].userEmail)

            // start the Update activity
            context.startActivity(intent)

        }

    } // end fun onBindViewHolder

    override fun getItemCount(): Int {

        // return the number of items in the arrayList
        return userList.size
    }

}