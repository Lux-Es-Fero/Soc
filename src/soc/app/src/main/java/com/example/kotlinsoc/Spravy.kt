package com.example.kotlinsoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

//hlavna stranka
class Spravy : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference
    private lateinit var userRecyclerView : RecyclerView
    private lateinit var userArrayList: ArrayList<Pouz>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_spravy)

        userRecyclerView = findViewById(R.id.userList)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf<Pouz>()
        getUserData()

    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance("https://kotlin-messenger-83d77-default-rtdb.europe-west1.firebasedatabase.app/").getReference("/Používatelia/")

        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot){

                if (snapshot.exists()){

                    for (userSnapshot in snapshot.children){

                        val pouz = userSnapshot.getValue(Pouz::class.java)
                        userArrayList.add(pouz!!)

                    }

                    userRecyclerView.adapter = Adapter(userArrayList)

                }

            }

            override fun onCancelled(error: DatabaseError){

            }
        })

    }

}


