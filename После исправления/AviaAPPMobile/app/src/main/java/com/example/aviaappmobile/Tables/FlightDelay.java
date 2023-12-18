package com.example.aviaappmobile.Tables;

public class FlightDelay {
    private int idFlightDelay;
    private String dateAndTimeDelay;
    private int flightId;
    private Flight flights;

    public FlightDelay (int idFlightDelay, String dateAndTimeDelay, int flightId, Flight flights){
        this.idFlightDelay = idFlightDelay;
        this.dateAndTimeDelay = dateAndTimeDelay;
        this.flightId = flightId;
        this.flights = flights;
    }

    public int getIdFlightDelay() {
        return idFlightDelay;
    }

    public void setIdFlightDelay(int idFlightDelay) {
        this.idFlightDelay = idFlightDelay;
    }

    public String getDateAndTimeDelay() {
        return dateAndTimeDelay;
    }

    public void setDateAndTimeDelay(String dateAndTimeDelay) {
        this.dateAndTimeDelay = dateAndTimeDelay;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Flight getFlights() {
        return flights;
    }

    public void setFlights(Flight flights) {
        this.flights = flights;
    }
}
