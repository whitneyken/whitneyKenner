package com.example.lab_2

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.lab_2.databinding.Fragment1Binding


class ClickFragment : Fragment() {

    private var buttonFunction : () -> Unit = {}

    fun setButtonFunction(newFunc: ()-> Unit) {
        buttonFunction = newFunc
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val binding = Fragment1Binding.inflate(inflater, container, false)

        binding.clickButton.setOnClickListener {
            buttonFunction()
        }

        return binding.root
    }


}