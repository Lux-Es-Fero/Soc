package com.example.kotlinsoc

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.content.LocusId
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.service.autofill.UserData
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinsoc.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


class RegistrujteSa : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().getReference("users")
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
        val uid = auth.currentUser?.uid


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
                Log.d("Main", "registracia sa podarila pre: ${it.result.user?.uid}")

                saveUserData(uid, uzmen, email)
            }
            //ked email je nespravneho tvaru
            .addOnFailureListener {
                Log.d("Main", "Registracia sa nepodarila : ${it.message}")
                Toast.makeText(
                    this,
                    "Registracia sa nepodarila : ${it.message}",
                    Toast.LENGTH_SHORT
                ).show()


            }


       /* FirebaseDatabase.getInstance().setPersistenceEnabled(true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        var uid = auth.currentUser?.uid
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        binding.regButtonReg.setOnClickListener {

            val meno = binding.uzmenEdittextReg.text.toString()
            val pouzmail = binding.emailEdittextReg.text.toString()
            val pouz = (meno); (pouzmail)

            if(uid != null){
                databaseReference.child(uid).setValue(pouz)
            }*/





        }
private fun saveUserData(uid: String?, username: String, email: String){
    if (uid == null) return

    val user = User(username, email, uid)
    databaseReference.child(uid).setValue(user)


}
    data class User(val uzmen: String, val email: String, val uid: String)
    }

