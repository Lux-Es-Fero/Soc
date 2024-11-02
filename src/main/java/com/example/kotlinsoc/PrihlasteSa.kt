package com.example.kotlinsoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class PrihlasteSa: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prihlastesa)

        // toto som musel definovat aby aom vedel pracovat s funkciami v tejto oblasti programu
        val button: Button = findViewById(R.id.prihls_button_prihls)
    }


    //gombik na prihlasene sa a vypis inputov do logcat
    fun prihl(view: View?) {
        val email = getTextValue(R.id.email_edittext_prihls)
        val heslo = getTextValue(R.id.heslo_edittext_prihls)

        //vypis pre logcat
        Log.d("PrihlasteSa", "Email : " + email)
        //Urcite potrebuje vediet logcat aj heslo? 
        Log.d("PrihlasteSa", "Heslo : $heslo")

        // ked chyba email a heslo
        if(email.isEmpty() || heslo.isEmpty()) {
            Toast.makeText(this, "Prosim zadajte email a heslo", Toast.LENGTH_SHORT).show()
            return
        }

        //prihlasenie pomocuo firebase
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,heslo)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener

                //ked je uspesny
                Log.d("PrihlasteSa","Prihlasenie sa podarilo pre: ${it.result.user?.uid}")
            }
            //ked email je nespravneho tvaru
            .addOnFailureListener{
                Log.d("PrihlasteSa", "Prihlasenie sa nepodarilo : ${it.message}")
            }
    }
    //spat na registracnu stranku
    fun regs(view: View?){
        startActivity(Intent(this, RegistrujteSa::class.java))

    }

    private fun getTextValue(textView: TextView): string{
        return findViewById<TextView>(textView).text.toString()
    }
}
