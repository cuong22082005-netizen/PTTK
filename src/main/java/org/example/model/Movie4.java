package org.example.model;

import java.util.ArrayList;

public class Movie4 {
    private int id;
    private String movieName;
    private String genre;
    private int duration;
    private String director;
    private String language;
    private String note;
    private ArrayList<ShowTime4> listShowTime;

    public Movie4() {
        this.listShowTime = new ArrayList<>();
    }

    public Movie4(int id, String movieName, String genre, int duration, String director,
                  String language, String note, ArrayList<ShowTime4> listShowTime) {
        this.id = id;
        this.movieName = movieName;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.language = language;
        this.note = note;
        this.listShowTime = listShowTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<ShowTime4> getListShowTime() {
        return listShowTime;
    }

    public void setListShowTime(ArrayList<ShowTime4> listShowTime) {
        this.listShowTime = listShowTime;
    }
}
