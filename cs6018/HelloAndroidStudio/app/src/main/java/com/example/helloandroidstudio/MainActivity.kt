package com.example.helloandroidstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun onClick(v: View){
        val clickedButton = v as Button
        val selectedStudent = clickedButton.text.toString()
        Log.d("student", "the selected student is..... $selectedStudent")

        val studentIntent = Intent(this, MainActivity2::class.java)
        val studentBundle = Bundle()
        studentBundle.putString("student", selectedStudent)
        studentIntent.putExtras(studentBundle)
        startActivity(studentIntent)
    }
}