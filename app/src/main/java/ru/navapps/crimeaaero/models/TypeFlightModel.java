package ru.navapps.crimeaaero.models;

import java.util.List;

public class TypeFlightModel {

    private List<FlightModel> yesterday;
    private List<FlightModel> today;
    private List<FlightModel> tomorrow;

    public TypeFlightModel(
            List<FlightModel> yesterday,
            List<FlightModel> today,
            List<FlightModel> tomorrow
    ){

        this.yesterday = yesterday;
        this.today = today;
        this.tomorrow = tomorrow;
    }

    public List<FlightModel> getYesterday() {
        return this.yesterday;
    }

    public List<FlightModel> getToday() {
        return this.today;
    }

    public List<FlightModel> getTomorrow() {
        return this.tomorrow;
    }
}