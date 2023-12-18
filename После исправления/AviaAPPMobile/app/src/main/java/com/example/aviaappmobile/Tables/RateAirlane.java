package com.example.aviaappmobile.Tables;

public class RateAirlane {
    private int idRateAirlane;
    private int rateId;
    private int airlineId;
    private Rate rates;
    private Airlane airlines;

    public RateAirlane (int idRateAirlane, int rateId, int airlineId, Rate rates, Airlane airlines){
        this.idRateAirlane = idRateAirlane;
        this.rateId = rateId;
        this.airlineId = airlineId;
        this.rates = rates;
        this.airlines = airlines;
    }

    public int getIdRateAirlane() {
        return idRateAirlane;
    }

    public void setIdRateAirlane(int idRateAirlane) {
        this.idRateAirlane = idRateAirlane;
    }

    public int getRateId() {
        return rateId;
    }

    public void setRateId(int rateId) {
        this.rateId = rateId;
    }

    public int getAirlineId() {
        return airlineId;
    }

    public void setAirlineId(int airlineId) {
        this.airlineId = airlineId;
    }

    public Rate getRates() {
        return rates;
    }

    public void setRates(Rate rates) {
        this.rates = rates;
    }

    public Airlane getAirlines() {
        return airlines;
    }

    public void setAirlines(Airlane airlines) {
        this.airlines = airlines;
    }
}
