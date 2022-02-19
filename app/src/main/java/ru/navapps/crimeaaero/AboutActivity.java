package ru.navapps.crimeaaero;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class AboutActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqactivity);

        ImageButton faqBtn = findViewById(R.id.btn_faq_back);
        ConstraintLayout phone = findViewById(R.id.phoneItem);
        ConstraintLayout email = findViewById(R.id.emailItem);
        ConstraintLayout map = findViewById(R.id.mapItem);
        ConstraintLayout dev = findViewById(R.id.devItem);
        ConstraintLayout devEmail = findViewById(R.id.devEmailItem);
        ConstraintLayout rate = findViewById(R.id.rate);
        Button rateUs = findViewById(R.id.rate_us);
        ImageView fb = findViewById(R.id.facebook);
        ImageView youtube = findViewById(R.id.youtube);
        ImageView inst = findViewById(R.id.inst);
        ImageView vk = findViewById(R.id.vk);

        faqBtn.setOnClickListener(v -> finish());

        phone.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("tel:" + getResources().getString(R.string.airportPhone)));
            startActivity(intent);
        });

        email.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("mailto:" + getResources().getString(R.string.airportEmail)));
            startActivity(intent);
        });

        map.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("geo:45.0358197,33.9816984"));
            startActivity(intent);
        });

        dev.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://nav-com.ru"));
            startActivity(intent);
        });

        devEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("mailto:" + getResources().getString(R.string.devEmail)));
            startActivity(intent);
        });

        rate.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=ru.navapps.crimeaaero"));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=ru.navapps.crimeaaero")));
            }
        });

        rateUs.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=ru.navapps.crimeaaero"));
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=ru.navapps.crimeaaero")));
            }
        });

        fb.setOnClickListener(v -> {
            String url = getResources().getString(R.string.fb);

            Uri uri = Uri.parse(url);
            try {
                ApplicationInfo applicationInfo = this.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
                if (applicationInfo.enabled) {
                    uri = Uri.parse("fb://facewebmodal/f?href=" + url);
                }
            } catch (PackageManager.NameNotFoundException ignored) {
            }
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        });

        youtube.setOnClickListener(v -> {
            String url = getResources().getString(R.string.youtube);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            intent.setPackage("com.google.android.youtube");
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(url)));
            }
        });

        inst.setOnClickListener(v -> {
            String url = getResources().getString(R.string.inst);

            Uri uri = Uri.parse("http://instagram.com/_u/" + url);
            Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

            likeIng.setPackage("com.instagram.android");

            try {
                startActivity(likeIng);
            } catch (ActivityNotFoundException e) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://instagram.com/" + url)));
            }
        });

        vk.setOnClickListener(v -> {
            String url = getResources().getString(R.string.vk);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);

        });

    }
}
