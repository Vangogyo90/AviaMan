package com.example.aviaappmobile.Tables;

public class Airlane {
    private int idAirline;
    private String nameAirline;

    public Airlane (int idAirline, String nameAirline){
        this.idAirline = idAirline;
        this.nameAirline = nameAirline;
    }

    public int getIdAirline() {
        return idAirline;
    }

    public void setIdAirline(int idAirline) {
        this.idAirline = idAirline;
    }

    public String getNameAirline() {
        return nameAirline;
    }

    public void setNameAirline(String nameAirline) {
        this.nameAirline = nameAirline;
    }
}
