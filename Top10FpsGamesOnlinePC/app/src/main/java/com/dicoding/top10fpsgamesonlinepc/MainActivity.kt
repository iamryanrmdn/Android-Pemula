package com.dicoding.top10fpsgamesonlinepc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvGames: RecyclerView
    private val list = ArrayList<Games>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGames = findViewById(R.id.rv_games)
        rvGames.setHasFixedSize(true)

        list.addAll(getListGames())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntentAboutPage = Intent(this@MainActivity, AboutPage::class.java)
                startActivity(moveIntentAboutPage)
            }
            R.id.exit -> {
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListGames(): ArrayList<Games> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataPhotoBackground = resources.obtainTypedArray(R.array.data_photo_background)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val dataDeveloper = resources.getStringArray(R.array.data_developer)
        val dataPublisher = resources.getStringArray(R.array.data_publisher)
        val dataUrl = resources.getStringArray(R.array.url_visit)
        val listGames = ArrayList<Games>()
        for (i in dataName.indices) {
            val games = Games(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1), dataPhotoBackground.getResourceId(i, -1), dataRating[i], dataDeveloper[i], dataPublisher[i], dataUrl[i])
            listGames.add(games)
        }
        return listGames
    }

    private fun showRecyclerList() {
        rvGames.layoutManager = LinearLayoutManager(this)
        val listGameAdapter = ListGameAdapter(list)
        rvGames.adapter = listGameAdapter
    }
}