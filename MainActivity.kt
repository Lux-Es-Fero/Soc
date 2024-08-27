package com.example.kotlinsoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannedString
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.text.buildSpannedString

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            }
    //prepis inputov do outputu (najprv vzhlada z activity_main.xml input a potom prepise do logcat)
    //funkcia "fun reg()" je gombik pre registraciu
    fun reg(view: View?){
        val emailTextView = findViewById<TextView>(R.id.email_edittext_reg)
        val email = emailTextView.text.toString()
        val hesloTextView = findViewById<TextView>(R.id.heslo_edittext_reg)
        val heslo = hesloTextView.text.toString()
        val uzmenTextView = findViewById<TextView>(R.id.uzmen_edittext_reg)
        val uzmen = uzmenTextView.text.toString()

    //toto je na vypis pre output (v logcat)
        Log.d("MainActivity", "Uzivatelske meno : $uzmen")
        Log.d("MainActivity", "Email : " + email)
        Log.d("MainActivity", "Heslo : $heslo")


    }
    //funkcia "fun prihls()" je kliknutelny text pre prsmerovanie na prihlasovaciu stranku
    fun prihls(view: View?){
        val intent = Intent(this, PrihlasteSa::class.java)
        startActivity(intent)

    }

}