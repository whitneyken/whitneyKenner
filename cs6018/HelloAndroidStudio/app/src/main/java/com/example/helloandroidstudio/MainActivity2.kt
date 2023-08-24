package com.example.helloandroidstudio

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    lateinit var editText : TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val studentBundle = intent.extras!!
        val studentName = studentBundle.getString("student")
        Log.d("received", "the student is $studentName")

        editText = findViewById(R.id.Robert)
        editText.text = studentName //set the text in edit text

    }

    fun goBack(v: View) {
        val backIntent = Intent(this, MainActivity::class.java)
        startActivity(backIntent)
    }
}