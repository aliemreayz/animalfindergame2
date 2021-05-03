package com.example.game

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView

class result : AppCompatActivity() {


    private var res: TextView? = null
    private var bestpoint: TextView? = null
    lateinit var sharedPreferences : SharedPreferences
    var BestPoint : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)




        var point = intent.getStringExtra("point")
        res = findViewById<TextView>(R.id.textView_point)
        res!!.setText(point)



        sharedPreferences = this.getSharedPreferences("game", Context.MODE_PRIVATE)

        BestPoint = sharedPreferences.getString("game","")



        if(BestPoint != ""){
            if((point!!.toInt())>(BestPoint!!.toInt())){
                sharedPreferences.edit().putString("game",point).apply()
            }
        }
        else{
            sharedPreferences.edit().putString("game",point).apply()
        }



        bestpoint = findViewById<TextView>(R.id.textView_result)
        bestpoint!!.setText(sharedPreferences.getString("game",""))







    }
}