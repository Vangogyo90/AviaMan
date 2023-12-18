package com.example.aviaappmobile.Tables;

public class Cities {
    private int idCity;
    private String nameCity;

    public Cities (int idCity, String nameCity){
        this.idCity = idCity;
        this.nameCity = nameCity;
    }

    public Cities (String nameCity){
        this.nameCity = nameCity;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        nameCity = nameCity;
    }
}
