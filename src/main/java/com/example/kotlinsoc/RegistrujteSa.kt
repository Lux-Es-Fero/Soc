package com.example.kotlinsoc

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class RegistrujteSa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registrujtesa)

    }


    //prepis inputov do outputu (najprv vyhlada z activity_main.xml input a potom prepise do logcat)
    //funkcia "fun reg()" je gombik pre registraciu
    fun reg(view: View?) {
        val emailTextView = findViewById<TextView>(R.id.email_edittext_reg)
        val email = emailTextView.text.toString()
        val hesloTextView = findViewById<TextView>(R.id.heslo_edittext_reg)
        val heslo = hesloTextView.text.toString()
        val uzmenTextView = findViewById<TextView>(R.id.uzmen_edittext_reg)
        val uzmen = uzmenTextView.text.toString()



        // ked chyba email a heslo
        if (email.isEmpty() || heslo.isEmpty()) {
            Toast.makeText(this, "Prosim zadajte email a heslo", Toast.LENGTH_SHORT).show()
            return
        }


        //toto je na vypis pre output (v logcat)
        Log.d("RegistrujteSa", "Uzivatelske meno : $uzmen")
        Log.d("RegistrujteSa", "Email : " + email)
        Log.d("RegistrujteSa", "Heslo : $heslo")

        //Firebase autentifikacia pre email a heslo
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, heslo)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener

                //ked je uspesny
                Log.d("RegistrujteSa", "registracia sa podarila pre: ${it.result.user?.uid}")


            }
            //ked email je nespravneho tvaru
            .addOnFailureListener {
                Log.d("RegistrujteSa", "Registracia sa nepodarila : ${it.message}")
                Toast.makeText(
                    this,
                    "Registracia sa nepodarila : ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()


            }
            databaza()

    }
        //ukladanie pouzivatelov do databazi
       private fun databaza() {
           val uzmenTextView = findViewById<TextView>(R.id.uzmen_edittext_reg)
           val uid = FirebaseAuth.getInstance().uid?:""
           val ref = FirebaseDatabase.getInstance().getReference("users")
           val user = User(uid, uzmenTextView.text.toString())
           ref.setValue(user)
               .addOnSuccessListener{
                   Log.d("RegistrujteSa", "Podarila sa databaza")

                   val intent = Intent(this,Spravy::class.java)
                   intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                   startActivity(intent)
               }

        }



}

class User(val uid: String, val username: String) {
    constructor(): this("","")
}