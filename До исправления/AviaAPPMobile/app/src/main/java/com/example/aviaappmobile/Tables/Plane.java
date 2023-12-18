package com.example.aviaappmobile.Tables;

public class Plane {
    private int idPlane;
    private String namePlane;

    public Plane (int idPlane, String namePlane){
        this.idPlane = idPlane;
        this.namePlane = namePlane;
    }

    public int getIdPlane() {
        return idPlane;
    }

    public void setIdPlane(int idPlane) {
        this.idPlane = idPlane;
    }

    public String getNamePlane() {
        return namePlane;
    }

    public void setNamePlane(String namePlane) {
        this.namePlane = namePlane;
    }
}
