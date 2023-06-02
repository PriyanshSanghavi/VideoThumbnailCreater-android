package com.example.videothumbnail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.videot.R
import wseemann.media.FFmpegMediaMetadataRetriever

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.image)
        val show:Button = findViewById(R.id.show)
        val url = findViewById<EditText>(R.id.videourl)
        try {
        show.setOnClickListener{
                displayVideoThumbnail(url.text.toString(), imageView)
        }
        }
        catch (e:Exception){
            Toast.makeText(applicationContext,"Please Enter Valid URL",Toast.LENGTH_SHORT).show()
        }

    }
}
fun displayVideoThumbnail(videoUrl: String, imageView: ImageView) {
    try{
        val retriever = FFmpegMediaMetadataRetriever()

        // Set the data source to the video URL
        retriever.setDataSource(videoUrl)

        // Retrieve the thumbnail image as a Bitmap
        val thumbnail: Bitmap? = retriever.frameAtTime

        // Display the thumbnail in the ImageView
        imageView.setImageBitmap(thumbnail)

        // Release the retriever resources
        retriever.release()

    }
    catch (e: Exception) {
        // Handle the exception, e.g., display an error message or use a default thumbnail
        e.printStackTrace()
        Toast.makeText(imageView.context, "Please Enter Valid URL", Toast.LENGTH_SHORT).show()
    }
}