package com.example.kotlinsoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class NovaSprava : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_sprava)


        supportActionBar?.title = "Vyberte Používatela"

       


    }
}