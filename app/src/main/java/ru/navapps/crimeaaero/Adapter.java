package ru.navapps.crimeaaero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

import ru.navapps.crimeaaero.models.JsonModel;

public class Adapter extends ArrayAdapter<JsonModel> {

    private LayoutInflater inflater;
    private int layout;
    private List<JsonModel> jsonObject;

    public Adapter(Context context, int resource, List<JsonModel> jsonObjects) {
        super(context, resource, jsonObjects);
        this.jsonObject = jsonObjects;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        TextView time = (TextView) view.findViewById(R.id.textView_listTime);
        TextView direction = (TextView) view.findViewById(R.id.textView_listCity);
        TextView status = (TextView) view.findViewById(R.id.textView_listStatus);
        TextView number = (TextView) view.findViewById(R.id.textView_listFlightNumber);

        JsonModel obj = jsonObject.get(position);

        if (obj.getType().equals("departure")) {
            if (obj.getDate().equals("yesterday")){
                time.setText(getTrueTime(obj.getDeparture().getYesterday().get(position).getDateTime()));
                direction.setText(obj.getDeparture().getYesterday().get(position).getDirection());
                status.setText(obj.getDeparture().getYesterday().get(position).getStatus());
                number.setText(obj.getDeparture().getYesterday().get(position).getFlight());
            } else if (obj.getDate().equals("today")){
                time.setText(getTrueTime(obj.getDeparture().getToday().get(position).getDateTime()));
                direction.setText(obj.getDeparture().getToday().get(position).getDirection());
                status.setText(obj.getDeparture().getToday().get(position).getStatus());
                number.setText(obj.getDeparture().getToday().get(position).getFlight());
            } else if (obj.getDate().equals("tomorrow")){
                time.setText(getTrueTime(obj.getDeparture().getTomorrow().get(position).getDateTime()));
                direction.setText(obj.getDeparture().getTomorrow().get(position).getDirection());
                status.setText(obj.getDeparture().getTomorrow().get(position).getStatus());
                number.setText(obj.getDeparture().getTomorrow().get(position).getFlight());
            }
        } else if (obj.getType().equals("arrival")) {
            if (obj.getDate().equals("yesterday")){
                time.setText(getTrueTime(obj.getArrival().getYesterday().get(position).getDateTime()));
                direction.setText(obj.getArrival().getYesterday().get(position).getDirection());
                status.setText(obj.getArrival().getYesterday().get(position).getStatus());
                number.setText(obj.getArrival().getYesterday().get(position).getFlight());
            } else if (obj.getDate().equals("today")){
                time.setText(getTrueTime(obj.getArrival().getToday().get(position).getDateTime()));
                direction.setText(obj.getArrival().getToday().get(position).getDirection());
                status.setText(obj.getArrival().getToday().get(position).getStatus());
                number.setText(obj.getArrival().getToday().get(position).getFlight());
            } else if (obj.getDate().equals("tomorrow")){
                time.setText(getTrueTime(obj.getArrival().getTomorrow().get(position).getDateTime()));
                direction.setText(obj.getArrival().getTomorrow().get(position).getDirection());
                status.setText(obj.getArrival().getTomorrow().get(position).getStatus());
                number.setText(obj.getArrival().getTomorrow().get(position).getFlight());
            }
        }

        return view;
    }

    public String getTrueTime(String time){
        return ((time.split("T")[1]).split("\\+")[0]).split(":")[0] + ":" + ((time.split("T")[1]).split("\\+")[0]).split(":")[1];
    }
}
