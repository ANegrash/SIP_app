package ru.navapps.crimeaaero;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        ImageButton faqBtn = findViewById(R.id.btn_faq_back);

        faqBtn.setOnClickListener(v -> finish());
    }
}
