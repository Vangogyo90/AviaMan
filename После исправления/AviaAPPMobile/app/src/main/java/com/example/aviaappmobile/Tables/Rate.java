package com.example.aviaappmobile.Tables;

public class Rate {
    private int idRate;
    private String nameRate;
    private double maximumWeightHandLuggage;
    private double maximumWeightCheckedBaggage;
    private boolean wiFi;
    private boolean breakfast;
    private boolean dinner;
    private boolean lunch;
    private boolean sockets;
    private boolean tv;
    private double costOfRate;

    public Rate (int idRate, String nameRate, double maximumWeightHandLuggage, double maximumWeightCheckedBaggage, boolean wiFi, boolean breakfast, boolean dinner,
                 boolean lunch, boolean sockets, boolean tv, double costOfRate){
        this.idRate = idRate;
        this.nameRate = nameRate;
        this.maximumWeightHandLuggage = maximumWeightHandLuggage;
        this.maximumWeightCheckedBaggage = maximumWeightCheckedBaggage;
        this.wiFi = wiFi;
        this.breakfast = breakfast;
        this.dinner = dinner;
        this.lunch = lunch;
        this.sockets = sockets;
        this.tv = tv;
        this.costOfRate = costOfRate;
    }

    public int getIdRate() {
        return idRate;
    }

    public void setIdRate(int idRate) {
        this.idRate = idRate;
    }

    public String getNameRate() {
        return nameRate;
    }

    public void setNameRate(String nameRate) {
        this.nameRate = nameRate;
    }

    public double getMaximumWeightHandLuggage() {
        return maximumWeightHandLuggage;
    }

    public void setMaximumWeightHandLuggage(double maximumWeightHandLuggage) {
        this.maximumWeightHandLuggage = maximumWeightHandLuggage;
    }

    public double getMaximumWeightCheckedBaggage() {
        return maximumWeightCheckedBaggage;
    }

    public void setMaximumWeightCheckedBaggage(double maximumWeightCheckedBaggage) {
        this.maximumWeightCheckedBaggage = maximumWeightCheckedBaggage;
    }

    public boolean isWiFi() {
        return wiFi;
    }

    public void setWiFi(boolean wiFi) {
        this.wiFi = wiFi;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
    }

    public boolean isSockets() {
        return sockets;
    }

    public void setSockets(boolean sockets) {
        this.sockets = sockets;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public double getCostOfRate() {
        return costOfRate;
    }

    public void setCostOfRate(double costOfRate) {
        this.costOfRate = costOfRate;
    }
}
