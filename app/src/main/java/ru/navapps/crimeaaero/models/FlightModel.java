package ru.navapps.crimeaaero.models;

public class FlightModel {

    private String type;
    private String dateTime;
    private String airline;
    private String airlineName;
    private String airlineLogo;
    private Integer number;
    private String flight;
    private String checkin;
    private Integer terminal;
    private String gate;
    private String airport;
    private String direction;
    private String status;
    private String delay;

    public FlightModel(
            String type,
            String dateTime,
            String airline,
            String airlineName,
            String airlineLogo,
            Integer number,
            String flight,
            String checkin,
            Integer terminal,
            String gate,
            String airport,
            String direction,
            String status,
            String delay
    ){

        this.type = type;
        this.dateTime = dateTime;
        this.airline = airline;
        this.airlineName = airlineName;
        this.airlineLogo = airlineLogo;
        this.number = number;
        this.flight = flight;
        this.checkin = checkin;
        this.terminal = terminal;
        this.gate = gate;
        this.airport = airport;
        this.direction = direction;
        this.status = status;
        this.delay = delay;
    }

    public String getType() {
        return this.type;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public String getAirlineName() {
        return this.airlineName;
    }

    public String getAirlineLogo() {
        return "https://new.sipaero.ru" + this.airlineLogo;
    }

    public String getFlight() {
        return this.flight;
    }

    public String getCheckin() {
        return this.checkin;
    }

    public String getGate() {
        return this.gate;
    }

    public String getDirection() {
        return this.direction;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDelay() {
        return this.delay;
    }
}