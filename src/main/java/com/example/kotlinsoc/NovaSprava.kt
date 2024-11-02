package com.example.kotlinsoc

import android.content.ClipData.Item
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.ViewHolder

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.FieldPosition

//stranka pre vyhladavanie pouzivatelov
//TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST TEST
class NovaSprava : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_sprava)


        supportActionBar?.title = "Vyberte Používatela"


        val adapter = GroupieAdapter<ViewHolder>()

        adapter.add(Pouzivatelia())
        adapter.add(Pouzivatelia())
        adapter.add(Pouzivatelia())


        recyclerview_novasprava.adapter = adapter

        fetchUsers()
    }

    private fun fetchUsers(){
        val ref = FirebaseDatabase.getInstance().getReference("/users")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {


                override fun onDataChange(p0: DataSnapshot) {
                    val adapter = GroupAdapter<ViewHolder>()

                    p0.children.forEach {
                        Log.d("NovaSprava", it.toString())
                        val pouz = it.getValue(User::class.java)
                        if (user != null) {
                            adapter.add(Pouzivatelia(user))
                        }
                        adapter.add(Pouzivatelia(user))


                        recyclerview_novasprava.adapter = adapter
                    }
                }
            )
    override fun onCancelled(p0: DatabaseError) {


    }}



class Pouzivatelia(val user: User): Item<ViewHolder>(){
    override fun bind(viewHolder: ViewHolder, position: Int) {
        val pouzmeno
        viewHolder.itemView.pouzivatelmeno_novasprava
    }

    override fun getLayout(): Int{
        return R.layout.pouzivatelia_novasprava
    }
}
