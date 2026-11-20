package org.example.model;

import java.util.ArrayList;

public class Cinema4 {
    private int id;
    private String cinemaName;
    private Address4 address;
    private String note;
    private ArrayList<ScreeningRoom4> listScreeningRoom;

    public Cinema4() {
        this.listScreeningRoom = new ArrayList<>();
    }

    public Cinema4(int id, String cinemaName, Address4 address, String note,
                   ArrayList<ScreeningRoom4> listScreeningRoom) {
        this.id = id;
        this.cinemaName = cinemaName;
        this.address = address;
        this.note = note;
        this.listScreeningRoom = listScreeningRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public Address4 getAddress() {
        return address;
    }

    public void setAddress(Address4 address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<ScreeningRoom4> getListScreeningRoom() {
        return listScreeningRoom;
    }

    public void setListScreeningRoom(ArrayList<ScreeningRoom4> listScreeningRoom) {
        this.listScreeningRoom = listScreeningRoom;
    }
}
