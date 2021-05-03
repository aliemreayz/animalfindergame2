package com.example.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class StartFragment : Fragment() {

    var button: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_start, container, false)
        // Inflate the layout for this fragment

        button = rootView.findViewById(R.id.button2_start)
        button?.setOnClickListener(){
            fragmentManager!!.beginTransaction()
                .add(R.id.frameLayout, BlankFragment())
                .remove(this@StartFragment)
                .commit()
        }

        return rootView
    }




}