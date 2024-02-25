//Ethan Lanier
//Assignment 2
//2/24/24
package com.hfad.assignment2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

class SplashScreenFragment : Fragment() {
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_splash_screen, container, false)
        val startButton = view.findViewById<Button>(R.id.button)

        startButton.setOnClickListener {
            val textView = view.findViewById(R.id.name_text) as TextView
            val name = textView.text.toString()
            view.findNavController()
                .navigate(SplashScreenFragmentDirections.actionSplashScreenToPlayBoard(name))
        }
        return view
    }
}