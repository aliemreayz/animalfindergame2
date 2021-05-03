package com.example.game

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*


class BlankFragment3 : Fragment() {
    private val bt: Button? = null
    private var resim: ImageView? = null
    private var resim2: ImageView? = null
    private var resim3: ImageView? = null
    private var resim4: ImageView? = null
    private val random = Random()
    private var countDownTimer: CountDownTimer? = null
    private var soru: TextView? = null
    private var sayac: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_blank3, container, false)
        resim = rootView.findViewById(R.id.imageView3_1)
        resim2 = rootView.findViewById(R.id.imageView3_2)
        resim3 = rootView.findViewById(R.id.imageView3_3)
        resim4 = rootView.findViewById(R.id.imageView3_4)
        sayac = rootView.findViewById(R.id.textView_sayac3)
        soru = rootView.findViewById(R.id.textView3)

        countDownTimer = object : CountDownTimer(14000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                sayac?.setText((millisUntilFinished / 1000).toString())
            }

            override fun onFinish() {
                countDownTimer!!.cancel()
                val intent = Intent(getActivity(), result::class.java)
                val str:String = MainActivity.point.toString()
                intent.putExtra("point", str);
                MainActivity.mp.stop()
                activity?.startActivity(intent)
                activity?.finish()
            }
        }.start()

        val dogru = MainActivity.list!![MainActivity.position]
        soru!!.setText(dogru)
        MainActivity.t1!!.speak("find the "+dogru+","+dogru+"makes sound", TextToSpeech.QUEUE_FLUSH,null,)
        MainActivity.mp = MediaPlayer.create(activity,resources.getIdentifier(dogru, "raw", activity?.getPackageName()))
        MainActivity.mp.start()

        val siklar: ArrayList<String> = ArrayList<String>()
        siklar.add(dogru)
        /*while (true) {
            val rnd = random.nextInt(15)
            if (MainActivity.list!![rnd] != dogru) {
                siklar.add(MainActivity.list!![rnd])
                break
            }
        }*/

        while(siklar.size<4){
            val rnd = random.nextInt(15)
            while(MainActivity.list!![rnd] != dogru && !(MainActivity.list!![rnd]in siklar)){
                siklar.add(MainActivity.list!![rnd])
                break
            }

        }


        Collections.shuffle(siklar)
        val imageresource = resources.getIdentifier(siklar[0], "drawable", activity!!.packageName)
        resim!!.setImageResource(imageresource)
        val imageresource2 = resources.getIdentifier(siklar[1], "drawable", activity!!.packageName)
        resim2!!.setImageResource(imageresource2)
        val imageresource3 = resources.getIdentifier(siklar[2], "drawable", activity!!.packageName)
        resim3!!.setImageResource(imageresource3)
        val imageresource4 = resources.getIdentifier(siklar[3], "drawable", activity!!.packageName)
        resim4!!.setImageResource(imageresource4)


        resim!!.setOnClickListener(View.OnClickListener {
            check(siklar[0])
            countDownTimer?.cancel()})
        resim2!!.setOnClickListener(View.OnClickListener {
            check(siklar[1])
            countDownTimer?.cancel()})
        resim3!!.setOnClickListener(View.OnClickListener {
            check(siklar[2])
            countDownTimer?.cancel()})
        resim4!!.setOnClickListener(View.OnClickListener {
            check(siklar[3])
            countDownTimer?.cancel()})
        return rootView
    }

    fun check(cevap: String?) {
        if (MainActivity.list!![MainActivity.position] == cevap) {

            MainActivity.mp.stop()
            MainActivity.t1!!.speak("you got it", TextToSpeech.QUEUE_FLUSH,null,)

            MainActivity.position++
            MainActivity.counter++
            MainActivity.point++
            if (MainActivity.counter == 3) {
                MainActivity.level++
                MainActivity.counter = 0

                replace()

            } else {

                refresh()
            }
        } else {
            MainActivity.mp.stop()
            MainActivity.t1!!.speak("im sorry, wrong answer", TextToSpeech.QUEUE_FLUSH,null,)
            val intent = Intent (getActivity(), result::class.java)
            val str:String = MainActivity.point.toString()
            intent.putExtra("point", str);
            getActivity()?.startActivity(intent)
        }
    }

    fun refresh() {
        fragmentManager!!.beginTransaction()
            .add(R.id.frameLayout, BlankFragment3())
            .remove(this@BlankFragment3)
            .commit()
    }

    fun replace() {
        fragmentManager!!.beginTransaction()
            .add(R.id.frameLayout, BlankFragment4())
            .remove(this@BlankFragment3)
            .commit()
    }
}