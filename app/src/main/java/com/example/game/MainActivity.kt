package com.example.game

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var frameLayout: FrameLayout? = null
    var fm = supportFragmentManager
    private var fragmentTutucu: FrameLayout? = null
    var button : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        t1 = TextToSpeech(this,this)




        fragmentTutucu = findViewById(R.id.frameLayout)

        list = ArrayList()
        list!!.add("bear")
        list!!.add("bee")
        list!!.add("bird")
        list!!.add("cat")
        list!!.add("chicken")
        list!!.add("cow")
        list!!.add("crocodile")
        list!!.add("dog")
        list!!.add("donkey")
        list!!.add("duck")
        list!!.add("elephant")
        list!!.add("frog")
        list!!.add("giraffe")
        list!!.add("horse")
        list!!.add("lion")
        list!!.add("monkey")
        list!!.add("sheep")
        list!!.add("squirrel")
        list!!.add("tiger")
        list!!.add("wolf")
        Collections.shuffle(list)

        showStart()




    }

    fun showStart() {
        val ft = fm.beginTransaction()
        ft.add(R.id.frameLayout, StartFragment())
        ft.commit()
    }

    companion object {
        var soru: String? = null
        var list: ArrayList<String>? = null
        var level = 1
        var position = 0
        var flag = true
        var counter = 0
        var point = 0
        var sayac_numara = 20
        var runnable : Runnable = Runnable {  }
        var handler = Handler()
        var t1 : TextToSpeech? = null
        lateinit var mp: MediaPlayer

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            t1!!.setLanguage(Locale.ENGLISH)

        }
    }


}