package org.example.model;

public class SeatType4 {
    private int id;
    private String nameSeatType;
    private float priceRate;
    private String note;

    public SeatType4() {
    }

    public SeatType4(int id, String nameSeatType, float priceRate, String note) {
        this.id = id;
        this.nameSeatType = nameSeatType;
        this.priceRate = priceRate;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameSeatType() {
        return nameSeatType;
    }

    public void setNameSeatType(String nameSeatType) {
        this.nameSeatType = nameSeatType;
    }

    public float getPriceRate() {
        return priceRate;
    }

    public void setPriceRate(float priceRate) {
        this.priceRate = priceRate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
