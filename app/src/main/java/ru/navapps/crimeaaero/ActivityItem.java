package ru.navapps.crimeaaero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ActivityItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        Intent intent = getIntent();
        TextView tvFlight = findViewById(R.id.textview_flight);
        TextView tvFrom = findViewById(R.id.textview_first_flight);
        TextView tvTo = findViewById(R.id.textview_last_flight);
        TextView tvStatus = findViewById(R.id.textView_flight_status);
        TextView tvCheckin = findViewById(R.id.textView_reception_desks_numbers);
        TextView tvExits = findViewById(R.id.textView_exit_number);
        TextView tvAirline = findViewById(R.id.textView_company_name);
        TextView tvDate = findViewById(R.id.textView_plan_date_current);
        ImageButton backBtn = findViewById(R.id.backBtn);
        ImageView airlineLogoImage = findViewById(R.id.airlineLogo);

        backBtn.setOnClickListener(view -> {
            Intent intentNew = new Intent(this, MainActivity.class);
            startActivity(intentNew);
            finish();
        });

        String sip = "Симферополь";
        String flightName = intent.getStringExtra("flightName");
        String direction = intent.getStringExtra("direction");
        String type = intent.getStringExtra("type");
        String status = intent.getStringExtra("status");
        String checkin = intent.getStringExtra("checkin");
        if (checkin.isEmpty()){
            checkin = "-";
        }
        String gate = intent.getStringExtra("exits");
        if (gate.isEmpty()){
            gate = "-";
        }
        String airlineName = intent.getStringExtra("airlineName");
        String airlineLogo = intent.getStringExtra("airlineLogo");
        String date = intent.getStringExtra("date");

        tvFlight.setText(flightName);
        if (type.equals("arrival")){
            tvFrom.setText(direction);
            tvTo.setText(sip);
        } else if (type.equals("departure")){
            tvFrom.setText(sip);
            tvTo.setText(direction);
        }
        tvStatus.setText(status);
        tvCheckin.setText(checkin);
        tvExits.setText(gate);
        tvAirline.setText(airlineName);
        tvDate.setText(getNormalDate(date));
        Picasso.get().load(airlineLogo).into(airlineLogoImage);
    }

    public String getNormalDate(String date){
        String[] datePart = date.split("T")[0].split("-");
        String month = "";
        switch (datePart[1]){
            case "01" : month = "января"; break;
            case "02" : month = "февраля"; break;
            case "03" : month = "марта"; break;
            case "04" : month = "апреля"; break;
            case "05" : month = "мая"; break;
            case "06" : month = "июня"; break;
            case "07" : month = "июля"; break;
            case "08" : month = "августа"; break;
            case "09" : month = "сентября"; break;
            case "10" : month = "октября"; break;
            case "11" : month = "ноября"; break;
            case "12" : month = "декабря"; break;
        }
        String[] timePart = date.split("T")[1].split("\\+")[0].split(":");
        String returnedDate = datePart[2] + " " + month;
        String returnedTime = timePart[0] + ":" + timePart[1];
        return returnedTime + ", " + returnedDate;
    }
}