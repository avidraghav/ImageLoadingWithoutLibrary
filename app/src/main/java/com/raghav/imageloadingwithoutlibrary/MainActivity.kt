package com.raghav.imageloadingwithoutlibrary

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class MainActivity : AppCompatActivity() {
    val handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        downloadAndShowBitmap(IMAGE_URL)
    }

    private fun downloadAndShowBitmap(imageUrl: String) {
        try {
            ExampleThread {
                val connection = URL(imageUrl).openConnection()
                connection.connect()
                val inputStream = connection.getInputStream()
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                handler.post(
                    Runnable {
                        findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
                    }
                )
            }.start()
        } catch (e: Exception) {
            Log.e("MainActivity", e.toString())
        }
    }

    companion object {
        private const val IMAGE_URL =
            "https://user-images.githubusercontent.com/49483235/149895306-79dd64bb-7629-42f3-97dd-1796f4a65b40.png"
    }
}
