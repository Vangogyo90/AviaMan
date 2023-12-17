package com.example.aviaappmobile.Tables;

import java.time.LocalDateTime;

public class Transfer {
    private int idTransfer;
    private String transferTime;
    private int cityId;
    private Cities cities;

    public Transfer (int idTransfer, String transferTime, int cityId, Cities cities){
        this.idTransfer = idTransfer;
        this.transferTime = transferTime;
        this.cityId = cityId;
        this.cities = cities;
    }

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        this.cities = cities;
    }
}
