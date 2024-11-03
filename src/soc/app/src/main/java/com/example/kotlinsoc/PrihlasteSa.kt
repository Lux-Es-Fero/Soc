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
            val emailTextView = findViewById<TextView>(R.id.email_edittext_prihls)
            val email = emailTextView.text.toString()
            val hesloTextView = findViewById<TextView>(R.id.heslo_edittext_prihls)
            val heslo = hesloTextView.text.toString()

            //vypis pre logcat
            Log.d("PrihlasteSa", "Email : " + email)


            // ked chyba email a heslo
            if(email.isEmpty() || heslo.isEmpty()) {
                Toast.makeText(this, "Prosim zadajte email a heslo", Toast.LENGTH_SHORT).show()
                return


                val intent = Intent(this,Spravy::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)

            }


            //prihlasenie pomocuo firebase
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,heslo)
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener

                    //ked je uspesny
                    Log.d("PrihlasteSa","Prihlasenie sa podarilo pre: ${it.result.user?.uid}")

                    val intent = Intent(this,Spravy::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)

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
        }
