package com.example.lab_2

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.lab_2.databinding.Fragment2Binding

class DrawFragment : Fragment() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = Fragment2Binding.inflate(inflater)

        val viewModel : SimpleViewModel by activityViewModels()
        viewModel.color.observe(viewLifecycleOwner){
            binding.customView.drawCircle(it)
        }

        binding.changeColorButton.setOnClickListener {
            viewModel.pickColor()
            binding.customView.drawCircle(viewModel.color.value!!)
            true
        }
        binding.backButton.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStackImmediate(null, 0)
        }




       return binding.root
    }

}