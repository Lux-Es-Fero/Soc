package com.example.kotlinsoc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannedString
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.text.buildSpannedString

class PrihlasteSa: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prihlastesa)

        //gombik na prihlasene sa a vypis inputov do logcat
        fun prihl(view: View?){
            val emailTextView = findViewById<TextView>(R.id.email_edittext_prihls)
            val email = emailTextView.text.toString()
            val hesloTextView = findViewById<TextView>(R.id.heslo_edittext_prihls)
            val heslo = hesloTextView.text.toString()

            Log.d("PrihlasteSa", "Email : " + email)
            Log.d("PrihlasteSa", "Heslo : $heslo")
        }
        fun regs(view: View?){
            val intent = Intent(this, PrihlasteSa::class.java)
            startActivity(intent)

        }
        }
}