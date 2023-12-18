package com.example.aviaappmobile.Tables;

import java.time.LocalDateTime;

public class Flight {
    private int idFlight;
    private String dateAndTimeDeparture;
    private String dateAndTimeArrival;
    private String numberOfFlight;
    private int cityOfArrival;
    private int cityOfDeparture;
    private int airlineId;
    private int planeId;
    private Cities arrivalCity;
    private Cities departureCity;
    private Airlane airlines;
    private Plane planes;

    public Flight (int idFlight, String dateAndTimeDeparture, String dateAndTimeArrival, String numberOfFlight, int cityOfArrival, int cityOfDeparture, int airlineId,
                   int planeId, Cities arrivalCity, Cities departureCity, Airlane airlanes, Plane planes){
        this.idFlight = idFlight;
        this.dateAndTimeDeparture = dateAndTimeDeparture;
        this.dateAndTimeArrival = dateAndTimeArrival;
        this.numberOfFlight = numberOfFlight;
        this.cityOfArrival = cityOfArrival;
        this.cityOfDeparture = cityOfDeparture;
        this.airlineId = airlineId;
        this.planeId = planeId;
        this.arrivalCity = arrivalCity;
        this.departureCity = departureCity;
        this.airlines = airlanes;
        this.planes = planes;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public String getNumberOfFlight() {
        return numberOfFlight;
    }

    public void setNumberOfFlight(String numberOfFlight) {
        this.numberOfFlight = numberOfFlight;
    }

    public int getCityOfArrival() {
        return cityOfArrival;
    }

    public void setCityOfArrival(int cityOfArrival) {
        this.cityOfArrival = cityOfArrival;
    }

    public int getCityOfDeparture() {
        return cityOfDeparture;
    }

    public void setCityOfDeparture(int cityOfDeparture) {
        this.cityOfDeparture = cityOfDeparture;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public int getPlaneId() {
        return planeId;
    }

    public void setPlaneId(int planeId) {
        this.planeId = planeId;
    }

    public Cities getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(Cities arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Cities getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(Cities departureCity) {
        this.departureCity = departureCity;
    }

    public Airlane getAirlines() {
        return airlines;
    }

    public void setAirlines(Airlane airlines) {
        this.airlines = airlines;
    }

    public Plane getPlanes() {
        return planes;
    }

    public void setPlanes(Plane planes) {
        this.planes = planes;
    }

    public String getDateAndTimeDeparture() {
        return dateAndTimeDeparture;
    }

    public void setDateAndTimeDeparture(String dateAndTimeDeparture) {
        this.dateAndTimeDeparture = dateAndTimeDeparture;
    }

    public String getDateAndTimeArrival() {
        return dateAndTimeArrival;
    }

    public void setDateAndTimeArrival(String dateAndTimeArrival) {
        this.dateAndTimeArrival = dateAndTimeArrival;
    }
}
