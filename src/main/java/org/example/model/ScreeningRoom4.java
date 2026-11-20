package org.example.model;

import java.util.ArrayList;

public class ScreeningRoom4 {
    private int id;
    private String roomName;
    private int capacity;
    private String note;
    private Cinema4 cinema;
    private ArrayList<Seat4> listSeat;

    public ScreeningRoom4() {
        this.listSeat = new ArrayList<>();
    }

    public ScreeningRoom4(int id, String roomName, int capacity, String note,
                          Cinema4 cinema, ArrayList<Seat4> listSeat) {
        this.id = id;
        this.roomName = roomName;
        this.capacity = capacity;
        this.note = note;
        this.cinema = cinema;
        this.listSeat = listSeat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Cinema4 getCinema() {
        return cinema;
    }

    public void setCinema(Cinema4 cinema) {
        this.cinema = cinema;
    }

    public ArrayList<Seat4> getListSeat() {
        return listSeat;
    }

    public void setListSeat(ArrayList<Seat4> listSeat) {
        this.listSeat = listSeat;
    }
}
