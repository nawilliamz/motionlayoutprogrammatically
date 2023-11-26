package com.example.motionlayoutprogrammatically

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.motionlayoutprogrammatically.databinding.ActivityDetailBinding
import com.example.motionlayoutprogrammatically.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.launchButton.setOnClickListener {

            val filename = "myFileName"
            val status = "Success"
            // Create the text message with a string.
            val launchIntent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra("FILENAME", filename)
                    putExtra("STATUS", status)

            }


// Try to invoke the intent.
            try {
                startActivity(launchIntent)
            } catch (e: ActivityNotFoundException) {
                // Define what your app should do if no activity can handle the intent.
                Log.i("MainActivity", "Unable to launch DetailActivity.")
            }
        }

    }




}