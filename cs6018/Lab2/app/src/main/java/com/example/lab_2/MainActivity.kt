package com.example.lab_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        binding.fragmentContainerView.getFragment<ClickFragment>().setButtonFunction {

            val drawFragment = DrawFragment()

            val transaction = this.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, drawFragment, "draw")
            transaction.addToBackStack(null)
            transaction.commit()
        }


        setContentView(binding.root)
    }
}