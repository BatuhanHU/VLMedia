package com.batuhan.vlmedia

import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.batuhan.vlmedia.util.AsyncUtil
import java.net.URL


class CharacterDetailsActivity : AppCompatActivity() {

    private var name = ""
    private var location = ""
    private var status = ""
    private var imageUrl = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_details)

        var textName = findViewById<TextView>(R.id.textViewTitle)
        var textLocation = findViewById<TextView>(R.id.textViewLocation)
        var textStatus = findViewById<TextView>(R.id.textViewStatus)
        var imageView = findViewById<ImageView>(R.id.imageView)


        val extras = intent.extras

        name = extras!!.getString("name").toString()
        location = extras.getString("location").toString()
        status = extras.getString("status").toString()
        imageUrl = extras.getString("imageUrl").toString()

        textName.text = name
        textLocation.text = location
        textStatus.text = status

        AsyncUtil{
            val url = URL(imageUrl)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            runOnUiThread { imageView.setImageBitmap(bmp) }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)





    }
}