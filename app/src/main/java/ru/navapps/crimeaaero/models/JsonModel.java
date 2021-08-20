package ru.navapps.crimeaaero.models;

public class JsonModel {

    private String root;
    private String date;
    private String type;
    private String limit;
    private TypeFlightModel departure;
    private TypeFlightModel arrival;

    public JsonModel(
            String root,
            String date,
            String type,
            String limit,
            TypeFlightModel departure,
            TypeFlightModel arrival
    ){

        this.root = root;
        this.date = date;
        this.type = type;
        this.limit = limit;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getDate() {
        return this.date;
    }

    public String getType() {
        return this.type;
    }

    public TypeFlightModel getDeparture() {
        return this.departure;
    }

    public TypeFlightModel getArrival() {
        return this.arrival;
    }
}