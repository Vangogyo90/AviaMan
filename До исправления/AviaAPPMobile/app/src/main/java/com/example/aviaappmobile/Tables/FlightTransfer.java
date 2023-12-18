package com.example.aviaappmobile.Tables;

public class FlightTransfer {
    private int idFlightTransfer;
    private int flightId;
    private int transferId;
    private Transfer transfers;

    public FlightTransfer (int idFlightTransfer, int flightId, int transferId, Transfer transfer){
        this.idFlightTransfer = idFlightTransfer;
        this.flightId = flightId;
        this.transferId = transferId;
        this.transfers = transfer;
    }

    public int getIdFlightTransfer() {
        return idFlightTransfer;
    }

    public void setIdFlightTransfer(int idFlightTransfer) {
        this.idFlightTransfer = idFlightTransfer;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public Transfer getTransfers() {
        return transfers;
    }

    public void setTransfers(Transfer transfers) {
        this.transfers = transfers;
    }
}
