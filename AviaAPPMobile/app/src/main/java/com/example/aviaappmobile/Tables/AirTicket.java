package com.example.aviaappmobile.Tables;

public class AirTicket {
    private int idAirTicket;
    private int flightId;
    private int rateAirlaneId;
    private double costOfAirTicket;
    private boolean haveTransfer;
    private String seatNumber;
    private boolean airTicketPurchase;
    private Flight flights;
    private RateAirlane rateAirlanes;

    public AirTicket (int idAirTicket, int flightId, int rateAirlaneId, double costOfAirTicket, boolean haveTransfer, String seatNumber, boolean airTicketPurchase,
                      Flight flights, RateAirlane rateAirlanes){
        this.idAirTicket = idAirTicket;
        this.flightId = flightId;
        this.rateAirlaneId = rateAirlaneId;
        this.costOfAirTicket = costOfAirTicket;
        this.haveTransfer = haveTransfer;
        this.seatNumber = seatNumber;
        this.airTicketPurchase = airTicketPurchase;
        this.flights = flights;
        this.rateAirlanes = rateAirlanes;
    }

    public AirTicket (int idAirTicket, int flightId, int rateAirlaneId, double costOfAirTicket, boolean haveTransfer, String seatNumber, boolean airTicketPurchase){
        this.idAirTicket = idAirTicket;
        this.flightId = flightId;
        this.rateAirlaneId = rateAirlaneId;
        this.costOfAirTicket = costOfAirTicket;
        this.haveTransfer = haveTransfer;
        this.seatNumber = seatNumber;
        this.airTicketPurchase = airTicketPurchase;
    }

    public int getIdAirTicket() {
        return idAirTicket;
    }

    public void setIdAirTicket(int idAirTicket) {
        this.idAirTicket = idAirTicket;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getRateAirlaneId() {
        return rateAirlaneId;
    }

    public void setRateAirlaneId(int rateAirlaneId) {
        this.rateAirlaneId = rateAirlaneId;
    }

    public double getCostOfAirTicket() {
        return costOfAirTicket;
    }

    public void setCostOfAirTicket(double costOfAirTicket) {
        this.costOfAirTicket = costOfAirTicket;
    }

    public boolean isHaveTransfer() {
        return haveTransfer;
    }

    public void setHaveTransfer(boolean haveTransfer) {
        this.haveTransfer = haveTransfer;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAirTicketPurchase() {
        return airTicketPurchase;
    }

    public void setAirTicketPurchase(boolean airTicketPurchase) {
        this.airTicketPurchase = airTicketPurchase;
    }

    public Flight getFlights() {
        return flights;
    }

    public void setFlights(Flight flights) {
        this.flights = flights;
    }

    public RateAirlane getRateAirlanes() {
        return rateAirlanes;
    }

    public void setRateAirlanes(RateAirlane rateAirlanes) {
        this.rateAirlanes = rateAirlanes;
    }
}
