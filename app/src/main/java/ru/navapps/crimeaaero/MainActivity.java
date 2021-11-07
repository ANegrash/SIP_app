package ru.navapps.crimeaaero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.navapps.crimeaaero.models.FlightModel;
import ru.navapps.crimeaaero.models.JsonModel;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs = null;
    String[] datesArray = {"Вчера", "Сегодня", "Завтра"};
    String[] datesArrayQuery = {"yesterday", "today", "tomorrow"};
    Integer dateVar = 0;

    String[] typesArray = {"Вылет", "Прилёт"};
    String[] typesArrayQuery = {"departure", "arrival"};
    Integer typeVar = 0;
    private List<JsonModel> arrayList = new ArrayList();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        prefs = getSharedPreferences("ru.navapps.crimeaaero", MODE_PRIVATE);
        int prefsDateVar = prefs.getInt("dateVar", 0);
        int prefsTypeVar = prefs.getInt("typeVar", 0);
        if (prefsDateVar != 0)
            dateVar = prefsDateVar;
        if (prefsTypeVar != 0)
            typeVar = prefsTypeVar;

        listView = findViewById(R.id.recyclerView_flightList);
        ConstraintLayout loadingLayout = findViewById(R.id.loading_layout);
        ConstraintLayout wifiLayout = findViewById(R.id.wifi_err_layout);
        Spinner spinnerDates = findViewById(R.id.spinner_dates);
        Spinner spinnerTypes = findViewById(R.id.spinner_flightTypes);
        ImageButton reloadBtn = findViewById(R.id.btn_reloadTable);
        ImageButton faqBtn = findViewById(R.id.btn_faq);

        listView.setVisibility(View.GONE);
        loadingLayout.setVisibility(View.VISIBLE);
        wifiLayout.setVisibility(View.GONE);

        ArrayAdapter<String> adapterDates = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, datesArray);
        adapterDates.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDates.setAdapter(adapterDates);
        spinnerDates.setSelection(dateVar);

        ArrayAdapter<String> adapterTypes = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typesArray);
        adapterTypes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypes.setAdapter(adapterTypes);
        spinnerTypes.setSelection(typeVar);

        reloadBtn.setOnClickListener(v -> {
            listView.setVisibility(View.GONE);
            loadingLayout.setVisibility(View.VISIBLE);
            wifiLayout.setVisibility(View.GONE);
            setRecyclerViewContent(typesArrayQuery[typeVar], datesArrayQuery[dateVar]);
        });

        faqBtn.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), AboutActivity.class);
            startActivity(i);
        });

        AdapterView.OnItemSelectedListener itemSelectedListenerDates = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
                loadingLayout.setVisibility(View.VISIBLE);
                wifiLayout.setVisibility(View.GONE);
                dateVar = position;
                prefs.edit().putInt("dateVar", dateVar).apply();
                setRecyclerViewContent(typesArrayQuery[typeVar], datesArrayQuery[dateVar]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerDates.setOnItemSelectedListener(itemSelectedListenerDates);

        AdapterView.OnItemSelectedListener itemSelectedListenerTypes = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listView.setVisibility(View.GONE);
                loadingLayout.setVisibility(View.VISIBLE);
                wifiLayout.setVisibility(View.GONE);
                typeVar = position;
                prefs.edit().putInt("typeVar", typeVar).apply();
                setRecyclerViewContent(typesArrayQuery[typeVar], datesArrayQuery[dateVar]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerTypes.setOnItemSelectedListener(itemSelectedListenerTypes);

        setRecyclerViewContent(typesArrayQuery[typeVar], datesArrayQuery[dateVar]);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    private void setRecyclerViewContent(
            String type,
            String date
    ) {
        Spinner spinnerDates = findViewById(R.id.spinner_dates);
        Spinner spinnerTypes = findViewById(R.id.spinner_flightTypes);
        spinnerDates.setEnabled(false);
        spinnerTypes.setEnabled(false);
        String url = "https://new.sipaero.ru/json/schedule/?";
        if (!type.equals("")) url += "type=" + type + "&";
        if (!date.equals("")) url += "date=" + date + "&";
        url += "lang=ru";

        Get test = new Get();

        test.run(url, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                runOnUiThread(() -> {
                    ListView listView = findViewById(R.id.recyclerView_flightList);
                    ConstraintLayout loadingLayout = findViewById(R.id.loading_layout);
                    ConstraintLayout wifiLayout = findViewById(R.id.wifi_err_layout);
                    ImageView errImg = findViewById(R.id.errorImage);
                    TextView errText = findViewById(R.id.errorText);

                    errImg.setImageDrawable(getResources().getDrawable(R.drawable.nowifi));
                    errText.setText("Проблемы с соединением");
                    listView.setVisibility(View.GONE);
                    loadingLayout.setVisibility(View.GONE);
                    wifiLayout.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.body() != null) {
                    try {
                        String result = response.body().string();
                        GsonBuilder builder = new GsonBuilder();
                        Gson gson = builder.create();
                        JsonModel jm = gson.fromJson(result, JsonModel.class);

                        arrayList.clear();

                        if (jm.getType() != null) {
                            if (jm.getType().equals("departure")) {
                                switch (jm.getDate()) {
                                    case "yesterday":
                                        for (int i = 0; i < jm.getDeparture().getYesterday().size(); i++) {
                                            arrayList.add(jm);
                                        }
                                        break;
                                    case "today":
                                        for (int i = 0; i < jm.getDeparture().getToday().size(); i++) {
                                            arrayList.add(jm);
                                        }
                                        break;
                                    case "tomorrow":
                                        for (int i = 0; i < jm.getDeparture().getTomorrow().size(); i++) {
                                            arrayList.add(jm);
                                        }
                                        break;
                                }
                            } else if (jm.getType().equals("arrival")) {
                                switch (jm.getDate()) {
                                    case "yesterday":
                                        for (int i = 0; i < jm.getArrival().getYesterday().size(); i++) {
                                            arrayList.add(jm);
                                        }
                                        break;
                                    case "today":
                                        for (int i = 0; i < jm.getArrival().getToday().size(); i++) {
                                            arrayList.add(jm);
                                        }
                                        break;
                                    case "tomorrow":
                                        for (int i = 0; i < jm.getArrival().getTomorrow().size(); i++) {
                                            arrayList.add(jm);
                                        }
                                        break;
                                }
                            }

                            runOnUiThread(() -> {
                                Adapter stateAdapter = new Adapter(getApplicationContext(), R.layout.list_element, arrayList);
                                listView.setAdapter(stateAdapter);
                                AdapterView.OnItemClickListener itemListener = (parent, v, position, id) -> {
                                    Intent intent = new Intent(getApplicationContext(), ActivityItem.class);

                                    FlightModel fm;
                                    if (jm.getType().equals("departure")) {
                                        switch (jm.getDate()) {
                                            case "yesterday":
                                                fm = jm.getDeparture().getYesterday().get(position);
                                                intent.putExtra("flightName", fm.getFlight());
                                                intent.putExtra("direction", fm.getDirection());
                                                intent.putExtra("type", fm.getType());
                                                intent.putExtra("status", fm.getStatus());
                                                intent.putExtra("checkin", fm.getCheckin());
                                                intent.putExtra("exits", fm.getGate());
                                                intent.putExtra("airlineName", fm.getAirlineName());
                                                intent.putExtra("airlineLogo", fm.getAirlineLogo());
                                                intent.putExtra("date", fm.getDateTime());
                                                break;
                                            case "today":
                                                fm = jm.getDeparture().getToday().get(position);
                                                intent.putExtra("flightName", fm.getFlight());
                                                intent.putExtra("direction", fm.getDirection());
                                                intent.putExtra("type", fm.getType());
                                                intent.putExtra("status", fm.getStatus());
                                                intent.putExtra("checkin", fm.getCheckin());
                                                intent.putExtra("exits", fm.getGate());
                                                intent.putExtra("airlineName", fm.getAirlineName());
                                                intent.putExtra("airlineLogo", fm.getAirlineLogo());
                                                intent.putExtra("date", fm.getDateTime());
                                                break;
                                            case "tomorrow":
                                                fm = jm.getDeparture().getTomorrow().get(position);
                                                intent.putExtra("flightName", fm.getFlight());
                                                intent.putExtra("direction", fm.getDirection());
                                                intent.putExtra("type", fm.getType());
                                                intent.putExtra("status", fm.getStatus());
                                                intent.putExtra("checkin", fm.getCheckin());
                                                intent.putExtra("exits", fm.getGate());
                                                intent.putExtra("airlineName", fm.getAirlineName());
                                                intent.putExtra("airlineLogo", fm.getAirlineLogo());
                                                intent.putExtra("date", fm.getDateTime());
                                                break;
                                        }
                                    } else if (jm.getType().equals("arrival")) {
                                        switch (jm.getDate()) {
                                            case "yesterday":
                                                fm = jm.getArrival().getYesterday().get(position);
                                                intent.putExtra("flightName", fm.getFlight());
                                                intent.putExtra("direction", fm.getDirection());
                                                intent.putExtra("type", fm.getType());
                                                intent.putExtra("status", fm.getStatus());
                                                intent.putExtra("checkin", fm.getCheckin());
                                                intent.putExtra("exits", fm.getGate());
                                                intent.putExtra("airlineName", fm.getAirlineName());
                                                intent.putExtra("airlineLogo", fm.getAirlineLogo());
                                                intent.putExtra("date", fm.getDateTime());
                                                break;
                                            case "today":
                                                fm = jm.getArrival().getToday().get(position);
                                                intent.putExtra("flightName", fm.getFlight());
                                                intent.putExtra("direction", fm.getDirection());
                                                intent.putExtra("type", fm.getType());
                                                intent.putExtra("status", fm.getStatus());
                                                intent.putExtra("checkin", fm.getCheckin());
                                                intent.putExtra("exits", fm.getGate());
                                                intent.putExtra("airlineName", fm.getAirlineName());
                                                intent.putExtra("airlineLogo", fm.getAirlineLogo());
                                                intent.putExtra("date", fm.getDateTime());
                                                break;
                                            case "tomorrow":
                                                fm = jm.getArrival().getTomorrow().get(position);
                                                intent.putExtra("flightName", fm.getFlight());
                                                intent.putExtra("direction", fm.getDirection());
                                                intent.putExtra("type", fm.getType());
                                                intent.putExtra("status", fm.getStatus());
                                                intent.putExtra("checkin", fm.getCheckin());
                                                intent.putExtra("exits", fm.getGate());
                                                intent.putExtra("airlineName", fm.getAirlineName());
                                                intent.putExtra("airlineLogo", fm.getAirlineLogo());
                                                intent.putExtra("date", fm.getDateTime());
                                                break;
                                        }
                                    }
                                    startActivity(intent);
                                };
                                listView.setOnItemClickListener(itemListener);
                                ListView listView = findViewById(R.id.recyclerView_flightList);
                                ConstraintLayout loadingLayout = findViewById(R.id.loading_layout);
                                ConstraintLayout wifiLayout = findViewById(R.id.wifi_err_layout);

                                listView.setVisibility(View.VISIBLE);
                                loadingLayout.setVisibility(View.GONE);
                                wifiLayout.setVisibility(View.GONE);

                            });
                        } else {
                            runOnUiThread(() -> {
                                ListView listView = findViewById(R.id.recyclerView_flightList);
                                ConstraintLayout loadingLayout = findViewById(R.id.loading_layout);
                                ConstraintLayout wifiLayout = findViewById(R.id.wifi_err_layout);
                                ImageView errImg = findViewById(R.id.errorImage);
                                TextView errText = findViewById(R.id.errorText);

                                errImg.setImageDrawable(getResources().getDrawable(R.drawable.error));
                                errText.setText("Какие-то проблемы на сервере. Попробуйте позже");
                                listView.setVisibility(View.GONE);
                                loadingLayout.setVisibility(View.GONE);
                                wifiLayout.setVisibility(View.VISIBLE);
                            });
                        }
                    } catch (JsonSyntaxException e) {
                        runOnUiThread(() -> {
                            ListView listView = findViewById(R.id.recyclerView_flightList);
                            ConstraintLayout loadingLayout = findViewById(R.id.loading_layout);
                            ConstraintLayout wifiLayout = findViewById(R.id.wifi_err_layout);
                            ImageView errImg = findViewById(R.id.errorImage);
                            TextView errText = findViewById(R.id.errorText);

                            errImg.setImageDrawable(getResources().getDrawable(R.drawable.error));
                            errText.setText("Какие-то проблемы на сервере. Попробуйте позже");
                            listView.setVisibility(View.GONE);
                            loadingLayout.setVisibility(View.GONE);
                            wifiLayout.setVisibility(View.VISIBLE);
                        });
                    }
                }
            }
        });
        spinnerDates.setEnabled(true);
        spinnerTypes.setEnabled(true);
    }
}