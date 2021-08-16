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

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getAirline() {
        return this.airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAirlineName() {
        return this.airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineLogo() {
        return this.airlineLogo;
    }

    public void setAirlineLogo(String airlineLogo) {
        this.airlineLogo = airlineLogo;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getFlight() {
        return this.flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getCheckin() {
        return this.checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public Integer getTerminal() {
        return this.terminal;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    public String getGate() {
        return this.gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getAirport() {
        return this.airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelay() {
        return this.delay;
    }

    public void setDelay(String delay) {
        this.delay = delay;
    }
}