package ru.navapps.crimeaaero

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class FAQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqactivity)

        val faqBtn = findViewById<View>(R.id.btn_faq_back) as ImageButton

        faqBtn.setOnClickListener {
            val intent = Intent(this, ActivityList::class.java)
            startActivity(intent)
            finish()
        }
    }
}