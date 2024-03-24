package com.dicoding.top10fpsgamesonlinepc

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataGames = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra("key_games", Games::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("key_games")
        }

        val tvDetailName:TextView = findViewById(R.id.name_val)
        val tvDetailDescription: TextView = findViewById(R.id.description_val)
        val ivDetailPhoto: ImageView = findViewById(R.id.img_item_photo)
        val ivDetailPhotoBackground: ImageView = findViewById(R.id.img_item_photo_background)
        val tvDetailRating: TextView = findViewById(R.id.rating_val)
        val tvDetailDeveloper: TextView = findViewById(R.id.developer_val)
        val tvDetailPublisher: TextView = findViewById(R.id.publisher_val)

        if (dataGames != null) {
            tvDetailName.text = dataGames.name
            tvDetailDescription.text = dataGames.description
            ivDetailPhoto.setImageResource(dataGames.photo)
            ivDetailPhotoBackground.setImageResource((dataGames.photoBackground))
            tvDetailRating.text = dataGames.rating
            tvDetailDeveloper.text = dataGames.developer
            tvDetailPublisher.text = dataGames.publisher
        }

        val btnShare: Button = findViewById(R.id.button_action_share)
        btnShare.setOnClickListener {
            if (dataGames != null) {
                val text: String = dataGames.url
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, text)
                startActivity(Intent.createChooser(intent,"Lihat Game ini $text"))
            }
        }

        val btnVisit: Button = findViewById(R.id.button_action_visit)
        btnVisit.setOnClickListener {
            if (dataGames != null) {
                val visitUrl = dataGames.url
                val intentVisit = Intent(Intent.ACTION_VIEW, Uri.parse("$visitUrl"))
                startActivity(intentVisit)
            }
        }
    }


}