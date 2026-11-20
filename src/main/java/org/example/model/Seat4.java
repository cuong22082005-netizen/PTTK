package org.example.model;

public class Seat4 {
    private int id;
    private String seatRow;
    private int seatNumber;
    private SeatType4 seatType;
    private ScreeningRoom4 screeningRoom;

    public Seat4() {
    }

    public Seat4(int id, String seatRow, int seatNumber, SeatType4 seatType, ScreeningRoom4 screeningRoom) {
        this.id = id;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.screeningRoom = screeningRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(String seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatType4 getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType4 seatType) {
        this.seatType = seatType;
    }

    public ScreeningRoom4 getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom4 screeningRoom) {
        this.screeningRoom = screeningRoom;
    }
}
