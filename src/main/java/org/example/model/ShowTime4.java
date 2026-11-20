package org.example.model;

import java.util.Date;

public class ShowTime4 {
    private int id;
    private Date showDate;
    private String startTime;
    private String endTime;
    private float basePrice;
    private int bonusPoints;
    private Movie4 movie;
    private ScreeningRoom4 screeningRoom;

    public ShowTime4() {
    }

    public ShowTime4(int id, Date showDate, String startTime, String endTime, float basePrice,
                     int bonusPoints, Movie4 movie, ScreeningRoom4 screeningRoom) {
        this.id = id;
        this.showDate = showDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.basePrice = basePrice;
        this.bonusPoints = bonusPoints;
        this.movie = movie;
        this.screeningRoom = screeningRoom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(int bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public Movie4 getMovie() {
        return movie;
    }

    public void setMovie(Movie4 movie) {
        this.movie = movie;
    }

    public ScreeningRoom4 getScreeningRoom() {
        return screeningRoom;
    }

    public void setScreeningRoom(ScreeningRoom4 screeningRoom) {
        this.screeningRoom = screeningRoom;
    }
}
