package org.example.model;

public class Ticket4 {
    private int id;
    private float ticketPrice;
    private String status;
    private ShowTime4 showTime;
    private Seat4 seat;

    public Ticket4() {
    }

    public Ticket4(int id, float ticketPrice, String status, ShowTime4 showTime, Seat4 seat) {
        this.id = id;
        this.ticketPrice = ticketPrice;
        this.status = status;
        this.showTime = showTime;
        this.seat = seat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ShowTime4 getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime4 showTime) {
        this.showTime = showTime;
    }

    public Seat4 getSeat() {
        return seat;
    }

    public void setSeat(Seat4 seat) {
        this.seat = seat;
    }
}
