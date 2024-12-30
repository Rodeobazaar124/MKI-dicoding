package com.dicoding.submissionazfa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun getActionBarHeight(): Int {
            val typedValue = TypedValue()
            return if (theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
                TypedValue.complexToDimensionPixelSize(typedValue.data, resources.displayMetrics)
            } else 0
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val actionBarHeight = getActionBarHeight()
            v.setPadding(
                systemBars.left,
                actionBarHeight - systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        rvHeroes = findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val moveIntent = Intent(this@MainActivity, about_me::class.java)
        startActivity(moveIntent)
        rvHeroes.layoutManager = LinearLayoutManager(this)
        return super.onOptionsItemSelected(item)
    }

    private fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataArticle = resources.getStringArray(R.array.data_article)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for (i in dataName.indices) {
            val hero = Hero(
                dataName[i],
                dataDescription[i],
                dataPhoto.getResourceId(i, -1),
                dataArticle[i]
            )
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
}