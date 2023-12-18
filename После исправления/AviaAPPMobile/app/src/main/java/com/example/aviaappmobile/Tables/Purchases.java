package com.example.aviaappmobile.Tables;

public class Purchases {
    private int idPurchase;
    private int airTicketId;
    private int userId;
    private double costPurchase;
    private AirTicket airTickets;
    private User users;

    public Purchases (int idPurchase, int airTicketId, int userId, double costPurchase, AirTicket airTicket, User users){
        this.idPurchase = idPurchase;
        this.airTicketId = airTicketId;
        this.userId = userId;
        this.costPurchase = costPurchase;
        this.airTickets = airTicket;
        this.users = users;
    }

    public Purchases (int airTicketId, int userId, double costPurchase){
        this.airTicketId = airTicketId;
        this.userId = userId;
        this.costPurchase = costPurchase;
    }

    public int getIdPurchase() {
        return idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getAirTicketId() {
        return airTicketId;
    }

    public void setAirTicketId(int airTicketId) {
        this.airTicketId = airTicketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCostPurchase() {
        return costPurchase;
    }

    public void setCostPurchase(double costPurchase) {
        this.costPurchase = costPurchase;
    }

    public AirTicket getAirTickets() {
        return airTickets;
    }

    public void setAirTickets(AirTicket airTickets) {
        this.airTickets = airTickets;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
