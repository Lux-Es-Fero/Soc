package com.example.kotlinsoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth

//hlavna stranka
class Spravy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spravy)
        //ked pouzivatel je prihlaseny tak nech sa nemusi zas prihlasovat
        //ked nie je prihlaseny tak sa presmeruje na prihlasovaciu stranku
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null){
            val intent = Intent (this, RegistrujteSa::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //menu kde je gombik pre odhlasenie a presmerovanie na vyhladavanie pouzivatelov
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       when (item.itemId){
           R.id.menu_nova_sprava ->{
               val intent = Intent(this, NovaSprava::class.java)
               startActivity(intent)
           }
           R.id.menu_odhlassa ->{
               FirebaseAuth.getInstance().signOut()
               val intent = Intent (this, RegistrujteSa::class.java)
               intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
               startActivity(intent)
           }
       }

        return super.onOptionsItemSelected(item)
    }
}