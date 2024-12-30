package com.dicoding.submissionazfa

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class detail_view : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_view)
        val HeroData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Hero>("key_culinary", Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>("key_culinary")
        }

        val tvDetailName = findViewById<TextView>(R.id.detail_name)
        val tvDetailDescription = findViewById<TextView>(R.id.detail_description)
        val tvDetailArticle = findViewById<TextView>(R.id.detail_article)
        val ivDetailPhoto = findViewById<ImageView>(R.id.detail_photo)

        tvDetailName.text = HeroData?.name
        tvDetailDescription.text = HeroData?.description
        tvDetailArticle.text = HeroData?.article
        ivDetailPhoto.setImageResource(HeroData!!.photo)
        ivDetailPhoto.contentDescription = HeroData.description
        getSupportActionBar()?.setTitle("Detail " + HeroData.name);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_detail)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}